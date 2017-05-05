package com.findout.anatomy_server.test;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.memory.InMemoryService;

public class TestHelper {

	private InMemoryService service;

	public TestHelper() {
		this.service = InMemoryService.getInstance();
	}

	public void fillData() {
		addModels();
		addAnatoms();
		addRelations();
	}

	public void deleteAllData() {
		service.getModels().clear();
		service.getAnatoms().clear();
		service.getAttributes().clear();
		service.getRelations().clear();
	}
	
	public void addModels() {
		for (int i = 0; i < 5; i++) {
			service.addModel();
		}
	}

	public void addAnatoms() {
		for (Model m : service.getModels()) {
			for (int i = 0; i < 3; i++) {
				service.addAnatom(m.getId());
			}
		}
	}
	
	public void addRelations() {
		for (Model m : service.getModels()) {
			service.addRelation(m.getAnatoms().get(0).getId(), m.getAnatoms().get(1).getId());
		}
	}

	public void addAnatomsToModel(int id) {
		for (int i = 0; i < 3; i++) {
			service.addAnatom(id);
		}
	}

	public void addRelationsToAnatom(int from, int to) {
		for (int i = 0; i < 3; i++) {
			service.addRelation(from, to);
		}
	}
}
