package com.findout.anatomy_server.services;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.services.interfaces.ModelServiceInterface;

public class ModelService implements ModelServiceInterface {

	private List<Model> models;
	
	public ModelService() {
		this.models = new ArrayList<Model>();
	}
	
	public List<Model> getModels() {
		return models;
	}
	
	public Model getModelWithId(int id) {
		for (Model model : models) {
			if (model.getId() == id) {
				return model;
			}
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

	public void deleteAllModels() {
		models.clear();
	}

	public void deleteModelWithId(int id) {
		for (int i = 0; i < models.size(); i++) {
			Model model = models.get(i);
			if (model.getId() == id) {
				models.remove(model);
			}
		}
	}
}
