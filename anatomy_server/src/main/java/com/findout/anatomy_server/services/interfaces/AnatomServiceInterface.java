package com.findout.anatomy_server.services.interfaces;

import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;

public interface AnatomServiceInterface {

	public List<Anatom> getAnatoms();
	public Anatom getAnatomWithId(int id);
	public Anatom addAnatom(Model model);
	public void addAnatom(Anatom anatom, Model model);
	public void deleteAnatomWithId(int id, Model model);
	public List<Anatom> getAnatomsForModel(Model model);
}
