package com.findout.anatomy_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findout.anatomy_server.entities.ModelEntity;

public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {
	
}
