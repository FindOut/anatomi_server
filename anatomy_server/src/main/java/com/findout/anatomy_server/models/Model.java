package com.findout.anatomy_server.models;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private int id;
	private List<Anatom> anatoms;
	
	public Model(int id) {
		this.id = id;
		this.anatoms = new ArrayList<Anatom>();
	}
	
	public int getId() {
		return id;
	}
	
	public List<Anatom> getAnatoms() {
		return anatoms;
	}
	
	public void setAnatoms(List<Anatom> anatoms) {
		this.anatoms = anatoms;
	}
	
	public void addAnatom(Anatom anatom) {
		anatoms.add(anatom);
	}
}
