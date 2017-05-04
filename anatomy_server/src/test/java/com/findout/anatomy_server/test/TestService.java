package com.findout.anatomy_server.test;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.rest.RestService;
import com.findout.anatomy_server.services.AnatomyService;

import groovy.transform.stc.FromAbstractTypeMethods;

public class TestService {

	private AnatomyService service;
	
	public TestService() {
		this.service = AnatomyService.getInstance();
	}
	
	public void addModels() {
		for (int i = 0; i < 5; i++) {
			Model model = new Model(i);
			service.addModel(model);
		}
	}
	
	public Model addModel() {
		List<Model> models = service.getModels();
		Model model = new Model(models.size());
		service.addModel(model);
		
		return model;
	}
	
	public void deleteAllModels() {
		service.deleteAllModels();
	}
	
	public void deleteModelWithId(int id) {
		service.deleteModelWithId(id);
	}
	
	public List<Model> getModels() {
		return service.getModels();
	}
	
	public Model getModelWithId(int id) {
		return service.getModelWithId(id);
	}
	
	public List<Anatom> getAnatomsForModel(int id) {
		return service.getAnatomsForModel(id);
	}
	
	public void addAnatomsToModel(int id) {
		for (int i = 0; i < 3; i++) {
			Anatom anatom = new Anatom(i);
			service.addAnatom(anatom, id);
		}
	}
	
	public Anatom addAnatom(Model model) {
		List<Anatom> anatoms = model.getAnatoms();
		Anatom anatom = new Anatom(anatoms.size());
		service.addAnatom(anatom, model.getId());
		
		return anatom;
	}
	
	public void addRelationsToAnatom(int from, int to) {
		for (int i = 0; i < 3; i++) {
			Relation relation = new Relation(i, from, to);
			service.addRelation(relation);
		}
	}
	
	public Relation addRelation(Anatom from, Anatom to) {
		List<Relation> relations = from.getRelations();
		Relation relation = new Relation(relations.size(), from.getId(), to.getId());
		service.addRelation(relation);
		
		return relation;
	}
}
