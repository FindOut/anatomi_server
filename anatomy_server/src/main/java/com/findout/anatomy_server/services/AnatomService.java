package com.findout.anatomy_server.services;

import java.util.ArrayList;
import java.util.List;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.services.interfaces.AnatomServiceInterface;

public class AnatomService implements AnatomServiceInterface {

	private List<Anatom> anatoms;
	
	public AnatomService() {
		this.anatoms = new ArrayList<Anatom>();
	}
	
	public List<Anatom> getAnatoms() {
		return anatoms;
	}
	
	public Anatom getAnatomWithId(int id) {
		for (Anatom anatom : anatoms) {
			if (anatom.getId() == id) {
				return anatom;
			}
		}
		
		return null;
	}

	public Anatom addAnatom(Model model) {
		Anatom anatom = new Anatom(anatoms.size());
		anatoms.add(anatom);
		model.addAnatom(anatom);
		
		return anatom;
	}

	public void addAnatom(Anatom anatom, Model model) {
		anatoms.add(anatom);
		model.addAnatom(anatom);
	}

	public void deleteAllAnatoms() {
		anatoms.clear();
	}

	public void deleteAnatomWithId(int id, Model model) {
		for (int i = 0; i < anatoms.size(); i++) {
			Anatom anatom = anatoms.get(i);
			if (anatom.getId() == id) {
				anatoms.remove(anatom);
				model.getAnatoms().remove(anatom);
			}
		}
	}
	
	public List<Anatom> getAnatomsForModel(Model model) {
		return model.getAnatoms();
	}
}
