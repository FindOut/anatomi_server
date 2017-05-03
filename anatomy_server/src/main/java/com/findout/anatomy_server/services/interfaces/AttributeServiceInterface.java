package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Attribute;

public interface AttributeServiceInterface {

	public List<Attribute> getAttributes();
	public Attribute getAttributelWithId(int id);
	public Attribute addAttribute();
	public void addAttribute(Attribute attribute);
	public void deleteAllAttributes();
	public void deleteAttributeWithId(int id);
}
