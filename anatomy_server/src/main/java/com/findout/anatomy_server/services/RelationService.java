package com.findout.anatomy_server.services;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.interfaces.RelationServiceInterface;

public class RelationService implements RelationServiceInterface {

	private List<Relation> relations;
	
	public RelationService() {
		this.relations = new ArrayList<Relation>();
	}
	
	public List<Relation> getRelations() {
		return relations;
	}

	public Relation getRelationWithId(int id) {
		for (Relation relation : relations) {
			if (relation.getId() == id) {
				return relation;
			}
		}
		
		return null;
	}

	public Relation addRelation(Anatom from, Anatom to) {
		Relation relation = new Relation(relations.size(), from.getId(), to.getId());
		relations.add(relation);
		from.addRelation(relation);
		to.addRelation(relation);
		
		return relation;
	}

	public void addRelation(Relation relation, Anatom from, Anatom to) {
		relations.add(relation);
		from.addRelation(relation);
		to.addRelation(relation);
	}

	public void deleteRelationWithId(int id) {
		
	}

	public List<Relation> getRelationsForAnatom(Anatom anatom) {
		return anatom.getRelations();
	}
}
