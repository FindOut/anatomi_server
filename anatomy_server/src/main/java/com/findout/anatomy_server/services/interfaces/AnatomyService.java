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
	
	public List<Relation> getRelationsForAnatom(int id);
	
	public Model getModelWithId(int id);
	
	public Model addModel();
	
	public void addModel(Model model);
	
	public void deleteModelWithId(int id);
	
	public Anatom addAnatom(int modelId);
	
	public void addAnatom(Anatom anatom, int modelId);
	
	public Anatom getAnatomWithId(int id);
	
	public void deleteAnatomFromModel(int anatomId, int modelId);
	
	public Relation addRelation(int from, int to);
	
	public void addRelation(Relation relation);
	
	public Relation getRelationWithId(int id);
}
