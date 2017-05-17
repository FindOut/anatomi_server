package se.findout.anatomy_server.services.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import se.findout.anatomy_server.services.db.entities.ModelEntity;

/**
 * Repository interface for CRUD operations on ModelEntity objects.
 * @author Maria Färdig
 *
 */
public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {
	
}
