package com.findout.anatomy_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findout.anatomy_server.entities.AnatomEntity;
import com.findout.anatomy_server.entities.ModelEntity;

public interface AnatomRepository extends JpaRepository<AnatomEntity, Integer>{

	public List<AnatomEntity> findByModel(ModelEntity model);
}
