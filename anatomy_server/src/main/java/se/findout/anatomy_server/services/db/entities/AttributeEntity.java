package se.findout.anatomy_server.services.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity representing an attribute in the database
 * @author Maria Färdig
 *
 */
@Entity
@Table(name = "attribute")
public class AttributeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attribute_id")
	private int id;

	@Column(name = "attribute_value")
	private int value;

	@ManyToOne
	@JoinColumn(name = "anatom_id")
	private AnatomEntity anatom;

	@ManyToOne
	@JoinColumn(name = "relation_id")
	private RelationEntity relation;

	public AttributeEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public AnatomEntity getAnatom() {
		return anatom;
	}

	public void setAnatom(AnatomEntity anatom) {
		this.anatom = anatom;
	}

	public RelationEntity getRelation() {
		return relation;
	}

	public void setRelation(RelationEntity relation) {
		this.relation = relation;
	}
}
