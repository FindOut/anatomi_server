package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Relation;

public interface RelationServiceInterface {
	
	public List<Relation> getRelations();
	public Relation getRelationWithId(int id);
	public Relation addRelation();
	public void addRelation(Relation relation);
	public void deleteAllRelations();
	public void deleteRelationWithId(int id);
}
