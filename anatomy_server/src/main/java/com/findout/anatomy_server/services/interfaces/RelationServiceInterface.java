package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Relation;

public interface RelationServiceInterface {
	
	public List<Relation> getRelations();
	public Relation getRelationWithId(int id);
	public Relation addRelation(Anatom from, Anatom to);
	public void addRelation(Relation relation, Anatom from, Anatom to);
	public void deleteRelationWithId(int id);
	public List<Relation> getRelationsForAnatom(Anatom anatom);
}
