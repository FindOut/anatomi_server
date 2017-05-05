package com.findout.anatomy_server.services.db;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.interfaces.AnatomyService;

public class DBService implements AnatomyService {

	public List<Model> getModels() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Anatom> getAnatomsForModel(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Attribute> getAttributesForAnatom(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Relation> getRelationsForAnatom(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Model getModelWithId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Model addModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addModel(Model model) {
		// TODO Auto-generated method stub
		
	}

	public void deleteModelWithId(int id) {
		// TODO Auto-generated method stub
		
	}

	public Anatom addAnatom(int modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addAnatom(Anatom anatom, int modelId) {
		// TODO Auto-generated method stub
		
	}

	public Anatom getAnatomWithId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAnatomFromModel(int anatomId, int modelId) {
		// TODO Auto-generated method stub
		
	}

	public Relation addRelation(int from, int to) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addRelation(Relation relation) {
		// TODO Auto-generated method stub
		
	}

	public Relation getRelationWithId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
