package com.findout.anatomy_server.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity representing a model in the database
 * @author Maria Färdig
 *
 */
@Entity
@Table(name = "model")
public class ModelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "model_id")
	private int id;

	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<AnatomEntity> anatoms;

	public ModelEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AnatomEntity> getAnatoms() {
		return anatoms;
	}

	public void setAnatoms(List<AnatomEntity> anatoms) {
		this.anatoms = anatoms;
	}

}
