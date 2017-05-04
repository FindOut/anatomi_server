package com.findout.anatomy_server.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.AnatomyService;

@RestController
public class RestService {

	public AnatomyService service;
	
	public RestService() {
		this.service = AnatomyService.getInstance();
	}
	
	@RequestMapping("/")
	public String home() {
		return "Welcome!";
	}
	
	@RequestMapping(value = "/models", method = RequestMethod.GET)
	public List<Model> getModels() {
        return service.getModels();
    }
	
	@RequestMapping(value = "/models", method = RequestMethod.POST)
	public Model addModel() {
		return service.addModel();
    }
	
	@RequestMapping(value = "/models/{id}", method = RequestMethod.GET)
	public Model getModel(@PathVariable int id) {
		return service.getModelWithId(id);
    }
	
	@RequestMapping(value = "/models/{id}", method = RequestMethod.DELETE)
	public void deleteModel(@PathVariable int id) {
		service.deleteModelWithId(id);
    }
	
	@RequestMapping(value = "/models/{id}/anatoms", method = RequestMethod.GET)
	public List<Anatom> getAnatomsForModel(@PathVariable int id) {
		return service.getAnatomsForModel(id);
	}
	
	@RequestMapping(value = "/models/{modelId}/anatoms", method = RequestMethod.POST)
	public Anatom addAnatomToModel(@PathVariable int modelId) {
		return service.addAnatom(modelId);
	}
	
	@RequestMapping(value = "/models/{modelId}/anatoms/{anatomId}", method = RequestMethod.GET)
	public Anatom getAnatomWithId(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getAnatomWithId(anatomId);
	}
	
	@RequestMapping(value = "/models/{modelId}/anatoms/{anatomId}", method = RequestMethod.DELETE)
	public void deleteAnatom(@PathVariable int modelId, @PathVariable int anatomId) {
		service.deleteAnatomFromModel(anatomId, modelId);
	}
	
	@RequestMapping(value = "/models/{modelId}/anatoms/{anatomId}/relations", method = RequestMethod.GET)
	public List<Relation> getRelationsForAnatom(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getRelationsForAnatom(anatomId);
	}
	
	@RequestMapping(value = "/models/{modelId}/anatoms/{anatomId}/relations/{relationId}", method = RequestMethod.GET)
	public Relation getRelationwithId(@PathVariable int modelId, @PathVariable int anatomId, @PathVariable int relationId) {
		return service.getRelationWithId(relationId);
	}
}
