package com.findout.anatomy_server.models;

public class Attribute {

	private int id;
	private int value;
	
	public Attribute(int id) {
		this.id = id;
		this.value = 0;
	}
	
	public Attribute(int id, int value) {
		this.id = id;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}
	
}
