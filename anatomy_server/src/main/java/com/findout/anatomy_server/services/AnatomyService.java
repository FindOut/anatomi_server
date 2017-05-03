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
	
	private AnatomyService() {
		this.modelService = new ModelService();
		this.anatomService = new AnatomService();
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
		return null;
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
	
	public void deleteAnatomFromModel(int anatomId, int modelId) {
		Model model = modelService.getModelWithId(modelId);
		anatomService.deleteAnatomWithId(anatomId, model);
	}
}
