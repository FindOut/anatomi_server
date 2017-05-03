package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;

public interface ModelServiceInterface {

	public List<Model> getModels();
	public Model getModelWithId(int id);
	public Model addModel();
	public void addModel(Model model);
	public void deleteAllModels();
	public void deleteModelWithId(int id);
}
