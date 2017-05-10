package com.findout.anatomy_server.services.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.findout.anatomy_server.entities.AnatomEntity;
import com.findout.anatomy_server.entities.AttributeEntity;
import com.findout.anatomy_server.entities.ModelEntity;
import com.findout.anatomy_server.entities.RelationEntity;
import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.repositories.AnatomRepository;
import com.findout.anatomy_server.repositories.AttributeRepository;
import com.findout.anatomy_server.repositories.ModelRepository;
import com.findout.anatomy_server.repositories.RelationRepository;
import com.findout.anatomy_server.services.interfaces.AnatomyService;

public class DBService implements AnatomyService {

	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private AnatomRepository anatomRepository;
	@Autowired
	private RelationRepository relationRepository;
	@Autowired
	private AttributeRepository attributeRepository;
	
	private List<Model> models;
	private List<Anatom> anatoms;
	private List<Relation> relations;
	private List<Attribute> attributes;
	private Converter converter;
	
	public DBService() {
		models = new ArrayList<Model>();
		anatoms = new ArrayList<Anatom>();
		attributes = new ArrayList<Attribute>();
		relations = new ArrayList<Relation>();
		converter = new Converter();
	}
	
	public List<Model> getModels() {
		List<ModelEntity> modelEntities = modelRepository.findAll();
		models = converter.convertModels(modelEntities);
		
		return models;
	}

	public List<Anatom> getAnatomsForModel(int id) {
		ModelEntity model = modelRepository.findOne(id);
		List<AnatomEntity> entities = anatomRepository.findByModel(model);
		anatoms = converter.convertAnatoms(entities);
		
		return anatoms;
	}

	public List<Attribute> getAttributesForAnatom(int id) {
		AnatomEntity anatom = anatomRepository.findOne(id);
		List<AttributeEntity> entities = attributeRepository.findByAnatom(anatom);
		attributes = converter.convertAttributes(entities);
		
		return attributes;
	}

	public List<Attribute> getAttributesForRelation(int id) {
		RelationEntity relation = relationRepository.findOne(id);
		List<AttributeEntity> attributeEntities = attributeRepository.findByRelation(relation);
		attributes = converter.convertAttributes(attributeEntities);
		
		return attributes;
	}
	
	public List<Relation> getRelationsForAnatom(int id) {
		AnatomEntity anatom = anatomRepository.findOne(id);
		relations = converter.convertRelations(anatom.getRelations());
		
		return relations;
	}

	public List<Relation> getRelationsFromAnatom(int id) {
		AnatomEntity anatom = anatomRepository.findOne(id);
		relations = converter.convertRelations(relationRepository.findByFromAnatom(anatom));
		
		return relations;
	}
	
	public List<Relation> getRelationsToAnatom(int id) {
		AnatomEntity anatom = anatomRepository.findOne(id);
		relations = converter.convertRelations(relationRepository.findByToAnatom(anatom));
		
		return relations;
	}
	
	public Model getModelWithId(int id) {
		ModelEntity entity = modelRepository.findOne(id);
		Model model = new Model(entity.getId());
		model.setAnatoms(converter.convertAnatoms(entity.getAnatoms()));
		
		return model;
	}

	public Model addModel() {
		ModelEntity modelEntity = modelRepository.save(new ModelEntity());
		Model model = new Model(modelEntity.getId());
		return model;
	}

	public void deleteModelWithId(int id) {
		modelRepository.delete(id);
	}

	public Anatom addAnatom(int modelId) {
		AnatomEntity entity = new AnatomEntity();
		ModelEntity model = modelRepository.findOne(modelId);
		entity.setModel(model);
		entity = anatomRepository.save(entity);
		Anatom anatom = new Anatom(entity.getId());
		
		return anatom;
	}

	public Anatom getAnatomWithId(int id) {
		AnatomEntity entity = anatomRepository.findOne(id);
		Anatom anatom = new Anatom(entity.getId());
		anatom.setAttributes(converter.convertAttributes(entity.getAttributes()));
		anatom.setRelations(converter.convertRelations(entity.getRelations()));
		
		return anatom;
	}

	public void deleteAnatomFromModel(int anatomId, int modelId) {
		anatomRepository.delete(anatomId);
	}

	public Relation addRelation(int from, int to) {
		AnatomEntity fromAnatom = anatomRepository.findOne(from);
		AnatomEntity toAnatom = anatomRepository.findOne(to);
		RelationEntity entity = new RelationEntity();
		entity.setFromAnatom(fromAnatom);
		entity.setToAnatom(toAnatom);
		entity = relationRepository.save(entity);
		Relation relation = new Relation(entity.getId(), entity.getFromAnatom().getId(), entity.getToAnatom().getId());
		
		return relation;
	}

	public Relation getRelationWithId(int id) {
		RelationEntity entity = relationRepository.findOne(id);
		Relation relation = new Relation(entity.getId(), entity.getFromAnatom().getId(), entity.getToAnatom().getId());
		relation.setAttributes(converter.convertAttributes(entity.getAttributes()));
		
		return relation;
	}

	public void deleteRelationWithId(int id) {
		relationRepository.delete(id);
	}

	public Attribute addAttributeToAnatom(int id, int value) {
		AnatomEntity anatom = anatomRepository.findOne(id);
		AttributeEntity entity = new AttributeEntity();
		entity.setAnatom(anatom);
		entity.setValue(value);
		entity = attributeRepository.save(entity);
		Attribute attribute = new Attribute(entity.getId(), entity.getValue());
		
		return attribute;
	}

	public Attribute addAttributeToRelation(int id, int value) {
		RelationEntity relation = relationRepository.findOne(id);
		AttributeEntity entity = new AttributeEntity();
		entity.setRelation(relation);;
		entity.setValue(value);
		entity = attributeRepository.save(entity);
		Attribute attribute = new Attribute(entity.getId(), entity.getValue());
		
		return attribute;
	}

	public Attribute getAttributeWithId(int id) {
		AttributeEntity entity = attributeRepository.findOne(id);
		Attribute attribute = new Attribute(entity.getId(), entity.getValue());
		
		return attribute;
	}

	public Attribute changeValueInAttribute(int id, int value) {
		AttributeEntity entity = attributeRepository.findOne(id);
		entity.setValue(value);
		entity = attributeRepository.save(entity);
		Attribute attribute = new Attribute(entity.getId(), entity.getValue());
		
		return attribute;
	}

	public void deleteAttributeFromRelation(int relationId, int attributeId) {
		attributeRepository.delete(attributeId);
	}

	public void deleteAttributeFromAnatom(int anatomId, int attributeId) {
		attributeRepository.delete(attributeId);
	}
}
