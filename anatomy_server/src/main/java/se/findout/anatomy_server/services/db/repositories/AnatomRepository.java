package se.findout.anatomy_server.services.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.findout.anatomy_server.services.db.entities.AnatomEntity;
import se.findout.anatomy_server.services.db.entities.ModelEntity;

/**
 * Repository interface for CRUD operations on AnatomEntity objects.
 * @author Maria Färdig
 *
 */
public interface AnatomRepository extends JpaRepository<AnatomEntity, Integer>{

	public List<AnatomEntity> findByModel(ModelEntity model);
}
