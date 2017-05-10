package com.findout.anatomy_server.entities;

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

@Entity
@Table(name = "relation")
public class RelationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "relation_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "from_anatom", nullable = false)
	private AnatomEntity fromAnatom;

	@ManyToOne
	@JoinColumn(name = "to_anatom", nullable = false)
	private AnatomEntity toAnatom;

	@OneToMany(mappedBy = "relation", cascade = CascadeType.ALL)
	private List<AttributeEntity> attributes;

	public RelationEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AnatomEntity getFromAnatom() {
		return fromAnatom;
	}

	public void setFromAnatom(AnatomEntity fromAnatom) {
		this.fromAnatom = fromAnatom;
	}

	public AnatomEntity getToAnatom() {
		return toAnatom;
	}

	public void setToAnatom(AnatomEntity toAnatom) {
		this.toAnatom = toAnatom;
	}

	public List<AttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeEntity> attributes) {
		this.attributes = attributes;
	}
}
