package com.findout.anatomy_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findout.anatomy_server.entities.AnatomEntity;
import com.findout.anatomy_server.entities.AttributeEntity;
import com.findout.anatomy_server.entities.RelationEntity;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {

	public List<AttributeEntity> findByAnatom(AnatomEntity anatom);
	public List<AttributeEntity> findByRelation(RelationEntity relation);
}
