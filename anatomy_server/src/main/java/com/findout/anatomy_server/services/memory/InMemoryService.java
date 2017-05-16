package com.findout.anatomy_server.services.memory;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.interfaces.AnatomyService;

/**
 * Implementation of AnatomyService, for storage only in memory (for testing or other purposes).
 * @author Maria Färdig
 *
 */
public class InMemoryService implements AnatomyService {

	private static InMemoryService instance;
	private List<Model> models;
	private List<Anatom> anatoms;
	private List<Relation> relations;
	private List<Attribute> attributes;
	
	private InMemoryService() {
		this.models = new ArrayList<Model>();
		this.anatoms = new ArrayList<Anatom>();
		this.relations = new ArrayList<Relation>();
		this.attributes = new ArrayList<Attribute>();
	}
	
	public static InMemoryService getInstance() {
		if (instance == null) {
			instance = new InMemoryService();
		}
		
		return instance;
	}
	
	public List<Model> getModels() {
		return models;
	}
	
	public List<Anatom> getAnatoms() {
		return anatoms;
	}
	
	public List<Relation> getRelations() {
		return relations;
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public List<Anatom> getAnatomsForModel(int id) {
		for (Model m : models) {
			if (m.getId() == id)
				return m.getAnatoms();
		}
		return null;
	}
	
	public List<Attribute> getAttributesForAnatom(int id) {
		for (Anatom a : anatoms) {
			if (a.getId() == id)
				return a.getAttributes();
		}
		
		return null;
	}
	
	public List<Attribute> getAttributesForRelation(int id) {
		for (Relation r : relations) {
			if (r.getId() == id)
				return r.getAttributes();
		}
		
		return null;
	}
	
	public List<Relation> getRelationsForAnatom(int id) {
		for (Anatom a : anatoms) {
			if (a.getId() == id)
				return a.getRelations();
		}
		
		return null;
	}
	
	public List<Relation> getRelationsFromAnatom(int id) {
		Anatom anatom = getAnatomWithId(id);
		List<Relation> outbound = new ArrayList<Relation>();
		for (Relation r : anatom.getRelations()) {
			if (r.getFrom() == id)
				outbound.add(r);
		}
		
		return outbound;
	}
	
	public List<Relation> getRelationsToAnatom(int id) {
		Anatom anatom = getAnatomWithId(id);
		List<Relation> inbound = new ArrayList<Relation>();
		for (Relation r : anatom.getRelations()) {
			if (r.getTo() == id)
				inbound.add(r);
		}
		
		return inbound;
	}
	
	public Model getModelWithId(int id) {
		for (Model m : models) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	public Model addModel() {
		Model model = new Model(models.size());
		models.add(model);
		
		return model;
	}
	
	public void deleteModelWithId(int id) {
		for (int i = 0; i < models.size(); i++) {
			Model model = models.get(i);
			if (model.getId() == id) {
				for (int j = 0; j < model.getAnatoms().size(); j++) {
					deleteAnatomFromModel(model.getAnatoms().get(j).getId(), id);
				}
				models.remove(model);
			}
		}
	}
	
	public Anatom addAnatom(int modelId) {
		Model model = getModelWithId(modelId);
		Anatom anatom = new Anatom(anatoms.size());
		anatoms.add(anatom);
		model.addAnatom(anatom);
		
		return anatom;
	}

	public Anatom getAnatomWithId(int id) {
		for (Anatom a : anatoms) {
			if (a.getId() == id) {
				return a;
			}
		}
		
		return null;
	}
	
	public void deleteAnatomFromModel(int anatomId, int modelId) {
		Anatom anatom = getAnatomWithId(anatomId);
		Model model = getModelWithId(modelId);
		for (int i = 0; i < anatom.getAttributes().size(); i++) {
			deleteAttributeFromAnatom(anatomId, anatom.getAttributes().get(i).getId());
		}
		for (int i = 0; i < anatom.getRelations().size(); i++) {
			deleteRelationWithId(anatom.getRelations().get(i).getId());
		}
		anatoms.remove(anatom);
		model.getAnatoms().remove(anatom);
	}
	
	public Relation addRelation(int from, int to) {
		Anatom fromAnatom = getAnatomWithId(from);
		Anatom toAnatom = getAnatomWithId(to);
		Relation relation = new Relation(relations.size(), from, to);
		relations.add(relation);
		fromAnatom.addRelation(relation);
		toAnatom.addRelation(relation);
		
		return relation;
	}
	
	public Relation getRelationWithId(int id) {
		for (Relation r : relations) {
			if (r.getId() == id) {
				return r;
			}
		}
		
		return null;
	}
	
	public void deleteRelationWithId(int id) {
		Relation relation = getRelationWithId(id);
		Anatom from = getAnatomWithId(relation.getFrom());
		Anatom to = getAnatomWithId(relation.getTo());
		for (int i = 0; i < relation.getAttributes().size(); i++) {
			deleteAttributeFromRelation(id, relation.getAttributes().get(i).getId());
		}
		from.getRelations().remove(relation);
		to.getRelations().remove(relation);
		relations.remove(relation);
	}

	public Attribute addAttributeToAnatom(int id, int value) {
		Anatom anatom = getAnatomWithId(id);
		Attribute attribute = new Attribute(attributes.size(), value);
		attributes.add(attribute);
		anatom.addAttribute(attribute);
		
		return attribute;
	}

	public Attribute addAttributeToRelation(int id, int value) {
		Relation relation = getRelationWithId(id);
		Attribute attribute = new Attribute(attributes.size(), value);
		attributes.add(attribute);
		relation.addAttribute(attribute);
		
		return attribute;
	}

	public Attribute getAttributeWithId(int id) {
		for (Attribute a : attributes) {
			if (a.getId() == id) {
				return a;
			}
		}
		
		return null;
	}
	
	public Attribute changeValueInAttribute(int id, int value) {
		Attribute attribute = getAttributeWithId(id);
		attribute.setValue(value);
		
		return attribute;
	}
	
	public void deleteAttributeFromRelation(int relationId, int attributeId) {
		Relation relation = getRelationWithId(relationId);
		Attribute attribute = getAttributeWithId(attributeId);
		relation.getAttributes().remove(attribute);
		attributes.remove(attribute);
	}
	
	public void deleteAttributeFromAnatom(int anatomId, int attributeId) {
		Anatom anatom = getAnatomWithId(anatomId);
		Attribute attribute = getAttributeWithId(attributeId);
		anatom.getAttributes().remove(attribute);
		attributes.remove(attribute);
	}
}
