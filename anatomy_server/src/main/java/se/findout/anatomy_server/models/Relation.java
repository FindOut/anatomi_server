package se.findout.anatomy_server.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple java object representing a relation between to anatoms in a diagram.
 * @author Maria Färdig
 *
 */
public class Relation {

	private int id;
	private int from;
	private int to;
	private List<Attribute> attributes;
	
	public Relation() {
		this.attributes = new ArrayList<Attribute>();
	}
	
	public Relation(int id) {
		this.id = id;
		this.attributes = new ArrayList<Attribute>();
	}
	
	public Relation(int id, int from, int to) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.attributes = new ArrayList<Attribute>();
	}

	public int getId() {
		return id;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
}
