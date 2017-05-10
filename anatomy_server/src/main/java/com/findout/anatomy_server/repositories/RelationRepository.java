package com.findout.anatomy_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findout.anatomy_server.entities.AnatomEntity;
import com.findout.anatomy_server.entities.RelationEntity;

public interface RelationRepository extends JpaRepository<RelationEntity, Integer> {

	public List<RelationEntity> findByToAnatom(AnatomEntity toAnatom);
	public List<RelationEntity> findByFromAnatom(AnatomEntity fromAnatom);
}
