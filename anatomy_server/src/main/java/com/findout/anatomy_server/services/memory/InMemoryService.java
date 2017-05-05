package com.findout.anatomy_server.services.memory;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.interfaces.AnatomyService;

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
	
	public List<Relation> getRelationsForAnatom(int id) {
		for (Anatom a : anatoms) {
			if (a.getId() == id)
				return a.getRelations();
		}
		
		return null;
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
	
	public void addModel(Model model) {
		models.add(model);
	}

	public void deleteModelWithId(int id) {
		for (int i = 0; i < models.size(); i++) {
			Model model = models.get(i);
			if (model.getId() == id) {
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
	
	public void addAnatom(Anatom anatom, int modelId) {
		Model model = getModelWithId(modelId);
		anatoms.add(anatom);
		model.addAnatom(anatom);
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
		Model model = getModelWithId(modelId);
		for (int i = 0; i < anatoms.size(); i++) {
			Anatom anatom = anatoms.get(i);
			if (anatom.getId() == anatomId) {
				anatoms.remove(anatom);
				model.getAnatoms().remove(anatom);
			}
		}
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
	
	public void addRelation(Relation relation) {
		Anatom fromAnatom = getAnatomWithId(relation.getFrom());
		Anatom toAnatom = getAnatomWithId(relation.getTo());
		relations.add(relation);
		fromAnatom.addRelation(relation);
		toAnatom.addRelation(relation);
	}
	
	public Relation getRelationWithId(int id) {
		for (Relation r : relations) {
			if (r.getId() == id) {
				return r;
			}
		}
		
		return null;
	}
}
