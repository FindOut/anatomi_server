package com.findout.anatomy_server.models;

import java.util.ArrayList;
import java.util.List;

public class Anatom {

	private int id;
	private List<Attribute> attributes;
	private List<Relation> relations;
	
	public Anatom(int id) {
		this.id = id;
		this.attributes = new ArrayList<Attribute>();
		this.relations = new ArrayList<Relation>();
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public void addRelation(Relation relation) {
		relations.add(relation);
	}
	
	public int getId() {
		return id;
	}
	
}
