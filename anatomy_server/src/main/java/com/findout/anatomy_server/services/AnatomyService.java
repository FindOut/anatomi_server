package com.findout.anatomy_server.services;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;

public class AnatomyService {

	private static AnatomyService instance;
	private ModelService modelService;
	private AnatomService anatomService;
	private RelationService relationService;
	
	private AnatomyService() {
		this.modelService = new ModelService();
		this.anatomService = new AnatomService();
		this.relationService = new RelationService();
	}
	
	public static AnatomyService getInstance() {
		if (instance == null) {
			instance = new AnatomyService();
		}
		
		return instance;
	}
	
	public List<Model> getModels() {
		return modelService.getModels();
	}
	
	public List<Anatom> getAnatomsForModel(int id) {
		Model model = modelService.getModelWithId(id);
		return anatomService.getAnatomsForModel(model);
	}
	
	public List<Attribute> getAttributesForAnatom(int id) {
		return null;
	}
	
	public List<Relation> getRelationsForAnatom(int id) {
		Anatom anatom = anatomService.getAnatomWithId(id);
		return relationService.getRelationsForAnatom(anatom);
	}
	
	public Model getModelWithId(int id) {
		return modelService.getModelWithId(id);
	}
	
	public Model addModel() {
		return modelService.addModel();
	}
	
	public void addModel(Model model) {
		modelService.addModel(model);
	}
	
	public void deleteAllModels() {
		modelService.deleteAllModels();
	}
	
	public void deleteModelWithId(int id) {
		modelService.deleteModelWithId(id);
	}
	
	public Anatom addAnatom(int modelId) {
		Model model = modelService.getModelWithId(modelId);
		return anatomService.addAnatom(model);
	}
	
	public void addAnatom(Anatom anatom, int modelId) {
		Model model = modelService.getModelWithId(modelId);
		anatomService.addAnatom(anatom, model);
	}
	
	public Anatom getAnatomWithId(int id) {
		return anatomService.getAnatomWithId(id);
	}
	
	public void deleteAnatomFromModel(int anatomId, int modelId) {
		Model model = modelService.getModelWithId(modelId);
		anatomService.deleteAnatomWithId(anatomId, model);
	}
	
	public Relation addRelation(int from, int to) {
		Anatom fromAnatom = anatomService.getAnatomWithId(from);
		Anatom toAnatom = anatomService.getAnatomWithId(to);
		return relationService.addRelation(fromAnatom, toAnatom);
	}
	
	public void addRelation(Relation relation) {
		Anatom fromAnatom = anatomService.getAnatomWithId(relation.getFrom());
		Anatom toAnatom = anatomService.getAnatomWithId(relation.getTo());
		relationService.addRelation(relation, fromAnatom, toAnatom);
	}
	
	public Relation getRelationWithId(int id) {
		return relationService.getRelationWithId(id);
	}
}
