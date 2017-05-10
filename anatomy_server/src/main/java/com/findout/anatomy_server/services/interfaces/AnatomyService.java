package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;

public interface AnatomyService {

	public List<Model> getModels();
	
	public List<Anatom> getAnatomsForModel(int id);
	
	public List<Attribute> getAttributesForAnatom(int id);
	
	public List<Attribute> getAttributesForRelation(int id);
	
	public List<Relation> getRelationsForAnatom(int id);
	
	public List<Relation> getRelationsFromAnatom(int id);
	
	public List<Relation> getRelationsToAnatom(int id);
	
	public Model getModelWithId(int id);
	
	public Model addModel();
	
	public void deleteModelWithId(int id);
	
	public Anatom addAnatom(int modelId);
	
	public Anatom getAnatomWithId(int id);
	
	public void deleteAnatomFromModel(int anatomId, int modelId);
	
	public Relation addRelation(int from, int to);
	
	public Relation getRelationWithId(int id);
	
	public void deleteRelationWithId(int id);
	
	public Attribute addAttributeToAnatom(int id, int value);
	
	public Attribute addAttributeToRelation(int id, int value);
	
	public Attribute getAttributeWithId(int id);
	
	public Attribute changeValueInAttribute(int id, int value);
	
	public void deleteAttributeFromRelation(int relationId, int attributeId);
	
	public void deleteAttributeFromAnatom(int anatomId, int attributeId);
}
