package se.findout.anatomy_server.services.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.findout.anatomy_server.services.db.entities.AnatomEntity;
import se.findout.anatomy_server.services.db.entities.RelationEntity;

/**
 * Repository interface for CRUD operations on RelationEntity objects.
 * @author Maria Färdig
 *
 */
public interface RelationRepository extends JpaRepository<RelationEntity, Integer> {

	public List<RelationEntity> findByToAnatom(AnatomEntity toAnatom);
	public List<RelationEntity> findByFromAnatom(AnatomEntity fromAnatom);
}
