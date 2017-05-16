package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;

/**
 * Interface for a service which is to be used by the REST-controller.
 * @author Maria Färdig
 *
 */
public interface AnatomyService {

	/**
	 * Retrieves all models with associated anatoms, relations and attributes.
	 * @return a list with models
	 */
	public List<Model> getModels();
	
	/**
	 * Retrieves all anatoms associated with a specific model, with their relations and attributes.
	 * @param id the id of the model
	 * @return a list with anatoms
	 */
	public List<Anatom> getAnatomsForModel(int id);
	
	/**
	 * Retrieves all attributes for a specific anatom.
	 * @param id the id of the anatom
	 * @return a list with attributes
	 */
	public List<Attribute> getAttributesForAnatom(int id);
	
	/**
	 * Retrieves all attributes for a specific relation.
	 * @param id the id of the relation
	 * @return a list with attributes
	 */
	public List<Attribute> getAttributesForRelation(int id);
	
	/**
	 * Retrieves all relations for a specific anatom, with all their attributes.
	 * @param id the id of the anatom
	 * @return a list with relations
	 */
	public List<Relation> getRelationsForAnatom(int id);
	
	/**
	 * Retrieves outbound relations for a specific anatom, with all their attributes.
	 * @param id the id of the anatom
	 * @return a list with relations
	 */
	public List<Relation> getRelationsFromAnatom(int id);
	
	/**
	 * Retrieves inbound relations for a specific anatom, with all their attributes.
	 * @param id the id of the anatom
	 * @return a list with relations
	 */
	public List<Relation> getRelationsToAnatom(int id);
	
	/**
	 * Retrieves a specific model with associated anatoms, relations and attributes.
	 * @param id the id of the model
	 * @return a single model
	 */
	public Model getModelWithId(int id);
	
	/**
	 * Creates an empty model.
	 * @return the model that was created
	 */
	public Model addModel();
	
	/**
	 * Deletes a specific model and all associated anatoms, relations and attributes.
	 * @param id the id of the model
	 */
	public void deleteModelWithId(int id);
	
	/**
	 * Creates an empty anatom and adds it to a model.
	 * @param modelId the id of the model
	 * @return the anatom that was created
	 */
	public Anatom addAnatom(int modelId);
	
	/**
	 * Retrieves a specific anatom with all relations and attributes.
	 * @param id the id of the anatom
	 * @return a single anatom
	 */
	public Anatom getAnatomWithId(int id);
	
	/**
	 * Deletes a specific anatom from a model, and all it's relations and attributes.
	 * @param anatomId the id of the anatom
	 * @param modelId the id of the model
	 */
	public void deleteAnatomFromModel(int anatomId, int modelId);
	
	/**
	 * Creates a relation between to anatoms.
	 * @param from the id of the anatom which the relation is going from
	 * @param to the id of the anatom which the relation is going to
	 * @return
	 */
	public Relation addRelation(int from, int to);
	
	/**
	 * Retrieves a specific relation with all it's attributes.
	 * @param id the id of the relation
	 * @return a single relation
	 */
	public Relation getRelationWithId(int id);
	
	/**
	 * Deletes a specific relation, and all it's attributes.
	 * @param id the id of the relation
	 */
	public void deleteRelationWithId(int id);
	
	/**
	 * Creates an attribute with a value and adds it to an anatom.
	 * @param id the id of the anatom
	 * @param value the value for the attribute
	 * @return the attribute that was created
	 */
	public Attribute addAttributeToAnatom(int id, int value);
	
	/**
	 * Creates an attribute with a value and adds it to a relation.
	 * @param id the id of the relation
	 * @param value the value for the attribute
	 * @return the attribute that was created
	 */
	public Attribute addAttributeToRelation(int id, int value);
	
	/**
	 * Retrieves a specific attribute.
	 * @param id the id of the attribute
	 * @return a single attribute
	 */
	public Attribute getAttributeWithId(int id);
	
	/**
	 * Updates the value for an attribute.
	 * @param id the id of the attribute
	 * @param value the new value
	 * @return the updated attribute
	 */
	public Attribute changeValueInAttribute(int id, int value);
	
	/**
	 * Deletes a specific attribute from a relation.
	 * @param relationId the id of the relation
	 * @param attributeId the id of the attribute
	 */
	public void deleteAttributeFromRelation(int relationId, int attributeId);
	
	/**
	 * Deletes a specific attribute from an anatom.
	 * @param relationId the id of the anatom
	 * @param attributeId the id of the attribute
	 */
	public void deleteAttributeFromAnatom(int anatomId, int attributeId);
}
