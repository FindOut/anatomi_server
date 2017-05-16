package com.findout.anatomy_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findout.anatomy_server.entities.AnatomEntity;
import com.findout.anatomy_server.entities.ModelEntity;

/**
 * Repository interface for CRUD operations on AnatomEntity objects.
 * @author Maria Färdig
 *
 */
public interface AnatomRepository extends JpaRepository<AnatomEntity, Integer>{

	public List<AnatomEntity> findByModel(ModelEntity model);
}
