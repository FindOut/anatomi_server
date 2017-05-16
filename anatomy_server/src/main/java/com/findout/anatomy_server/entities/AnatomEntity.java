package com.findout.anatomy_server.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity representing an anatom in the database
 * @author Maria Färdig
 *
 */
@Entity
@Table(name = "anatom")
public class AnatomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "anatom_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "model_id", nullable = false)
	private ModelEntity model;

	@OneToMany(mappedBy = "anatom", cascade = CascadeType.ALL)
	private List<AttributeEntity> attributes;

	@OneToMany(mappedBy = "fromAnatom", cascade = CascadeType.ALL)
	private List<RelationEntity> outboundRelations;

	@OneToMany(mappedBy = "toAnatom", cascade = CascadeType.ALL)
	private List<RelationEntity> inboundRelations;

	@Transient
	private List<RelationEntity> relations = new ArrayList<RelationEntity>();
	
	public AnatomEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModelEntity getModel() {
		return model;
	}

	public void setModel(ModelEntity model) {
		this.model = model;
	}

	public List<RelationEntity> getRelations() {
		relations.addAll(outboundRelations);
		relations.addAll(inboundRelations);
		return relations;
	}

	public void setRelations(List<RelationEntity> relations) {
		this.relations = relations;
	}

	public List<RelationEntity> getOutboundRelations() {
		return outboundRelations;
	}

	public void setOutboundRelations(List<RelationEntity> outboundRelations) {
		this.outboundRelations = outboundRelations;
	}

	public List<RelationEntity> getInboundRelations() {
		return inboundRelations;
	}

	public void setInboundRelations(List<RelationEntity> inboundRelations) {
		this.inboundRelations = inboundRelations;
	}

	public List<AttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeEntity> attributes) {
		this.attributes = attributes;
	}
}
