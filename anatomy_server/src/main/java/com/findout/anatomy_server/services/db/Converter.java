package com.findout.anatomy_server.services.db;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.entities.AnatomEntity;
import com.findout.anatomy_server.entities.AttributeEntity;
import com.findout.anatomy_server.entities.ModelEntity;
import com.findout.anatomy_server.entities.RelationEntity;
import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;

/**
 * Helper class for converting to simple java objects from the JPA entity objects.
 * @author Maria Färdig
 *
 */
public class Converter {

	public List<Model> convertModels(List<ModelEntity> entities) {
		List<Model> models = new ArrayList<Model>();
		for (ModelEntity m : entities) {
			Model model = new Model(m.getId());
			model.setAnatoms(convertAnatoms(m.getAnatoms()));
			models.add(model);
		}
		
		return models;
	}
	
	public List<Anatom> convertAnatoms(List<AnatomEntity> entities) {
		List<Anatom> anatoms = new ArrayList<Anatom>();
		for (AnatomEntity a : entities) {
			Anatom anatom = new Anatom(a.getId());
			anatom.setAttributes(convertAttributes(a.getAttributes()));
			anatom.setRelations(convertRelations(a.getRelations()));
			anatoms.add(anatom);
		}
		
		return anatoms;
	}
	
	public List<Relation> convertRelations(List<RelationEntity> entities) {
		List<Relation> relations = new ArrayList<Relation>();
		for (RelationEntity r : entities) {
			Relation relation = new Relation(r.getId(), r.getFromAnatom().getId(),
				r.getToAnatom().getId());
			relation.setAttributes(convertAttributes(r.getAttributes()));
			relations.add(relation);
		}
		
		return relations;
	}
	
	public List<Attribute> convertAttributes(List<AttributeEntity> entities) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		for (AttributeEntity a : entities) {
			Attribute attribute = new Attribute(a.getId(), a.getValue());
			attributes.add(attribute);
		}
		
		return attributes;
	}
}
