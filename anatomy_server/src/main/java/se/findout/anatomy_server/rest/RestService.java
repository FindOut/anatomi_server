package se.findout.anatomy_server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.findout.anatomy_server.models.Anatom;
import se.findout.anatomy_server.models.Attribute;
import se.findout.anatomy_server.models.Model;
import se.findout.anatomy_server.models.Relation;
import se.findout.anatomy_server.services.interfaces.AnatomyService;

/**
 * Class for controlling all REST resources.
 * @author Maria Färdig
 *
 */
@RestController
public class RestService {

	@Autowired
	public AnatomyService service;
	
	@RequestMapping("")
	public String home() {
		return "This is an api for accessing data from anatomy_server.";
	}
	
	@RequestMapping(value = "models", method = RequestMethod.GET)
	public List<Model> getModels() {
        return service.getModels();
    }
	
	@RequestMapping(value = "models", method = RequestMethod.POST)
	public Model addModel() {
		return service.addModel();
    }
	
	@RequestMapping(value = "models/{id}", method = RequestMethod.GET)
	public Model getModel(@PathVariable int id) {
		return service.getModelWithId(id);
    }
	
	@RequestMapping(value = "models/{id}", method = RequestMethod.DELETE)
	public void deleteModel(@PathVariable int id) {
		service.deleteModelWithId(id);
    }
	
	@RequestMapping(value = "models/{id}/anatoms", method = RequestMethod.GET)
	public List<Anatom> getAnatomsForModel(@PathVariable int id) {
		return service.getAnatomsForModel(id);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms", method = RequestMethod.POST)
	public Anatom addAnatomToModel(@PathVariable int modelId) {
		return service.addAnatom(modelId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}", method = RequestMethod.GET)
	public Anatom getAnatomWithId(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getAnatomWithId(anatomId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}", method = RequestMethod.DELETE)
	public void deleteAnatomFromModel(@PathVariable int modelId, @PathVariable int anatomId) {
		service.deleteAnatomFromModel(anatomId, modelId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}/relations", method = RequestMethod.GET)
	public List<Relation> getRelationsForAnatom(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getRelationsForAnatom(anatomId);
	}
	
	@RequestMapping(value = "relations", method = RequestMethod.POST)
	public Relation addRelation(@RequestBody Relation relation) {
		return service.addRelation(relation.getFrom(), relation.getTo());
	}
	
	@RequestMapping(value = "relations/{relationId}", method = RequestMethod.GET)
	public Relation getRelationwithId(@PathVariable int relationId) {
		return service.getRelationWithId(relationId);
	}
	
	@RequestMapping(value = "relations/{relationId}", method = RequestMethod.DELETE)
	public void deleteRelationWithId(@PathVariable int relationId) {
		service.deleteRelationWithId(relationId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}/attributes", method = RequestMethod.GET)
	public List<Attribute> getAttributesForAnatom(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getAttributesForAnatom(anatomId);
	}
	
	@RequestMapping(value = "relations/{relationId}/attributes", method = RequestMethod.GET)
	public List<Attribute> getAttributesForRelation(@PathVariable int relationId) {
		return service.getAttributesForRelation(relationId);
	}
	
	@RequestMapping(value = "attributes/{attributeId}", method = RequestMethod.GET)
	public Attribute getAttributeWithId(@PathVariable int attributeId) {
		return service.getAttributeWithId(attributeId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}/attributes", method = RequestMethod.POST)
	public Attribute addAttributeToAnatom(@PathVariable int modelId, @PathVariable int anatomId, @RequestBody Attribute attribute) {
		return service.addAttributeToAnatom(anatomId, attribute.getValue());
	}
	
	@RequestMapping(value = "relations/{relationId}/attributes", method = RequestMethod.POST)
	public Attribute addAttributeToRelation(@PathVariable int relationId, @RequestBody Attribute attribute) {
		return service.addAttributeToRelation(relationId, attribute.getValue());
	}
	
	@RequestMapping(value = "attributes/{attributeId}", method = RequestMethod.PUT)
	public Attribute changeValueInAttribute(@PathVariable int attributeId, @RequestBody Attribute attribute) {
		return service.changeValueInAttribute(attributeId, attribute.getValue());
	}
	
	@RequestMapping(value = "relations/{relationId}/attributes/{attributeId}", method = RequestMethod.DELETE)
	public void deleteAttributeFromRelation(@PathVariable int relationId, @PathVariable int attributeId) {
		service.deleteAttributeFromRelation(relationId, attributeId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}/attributes/{attributeId}", method = RequestMethod.DELETE)
	public void deleteAttributeFromAnatom(@PathVariable int modelId, @PathVariable int anatomId, @PathVariable int attributeId) {
		service.deleteAttributeFromAnatom(anatomId, attributeId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}/relations/outbound", method = RequestMethod.GET)
	public List<Relation> getRelationsFromAnatom(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getRelationsFromAnatom(anatomId);
	}
	
	@RequestMapping(value = "models/{modelId}/anatoms/{anatomId}/relations/inbound", method = RequestMethod.GET)
	public List<Relation> getRelationsToAnatom(@PathVariable int modelId, @PathVariable int anatomId) {
		return service.getRelationsToAnatom(anatomId);
	}
}
