package se.findout.anatomy_server.services.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.findout.anatomy_server.services.db.entities.AnatomEntity;
import se.findout.anatomy_server.services.db.entities.AttributeEntity;
import se.findout.anatomy_server.services.db.entities.RelationEntity;

/**
 * Repository interface for CRUD operations on AttributeEntity objects.
 * @author Maria Färdig
 *
 */
public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {

	public List<AttributeEntity> findByAnatom(AnatomEntity anatom);
	public List<AttributeEntity> findByRelation(RelationEntity relation);
}
