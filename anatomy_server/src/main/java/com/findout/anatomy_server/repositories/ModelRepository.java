package com.findout.anatomy_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findout.anatomy_server.entities.ModelEntity;

/**
 * Repository interface for CRUD operations on ModelEntity objects.
 * @author Maria Färdig
 *
 */
public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {
	
}
