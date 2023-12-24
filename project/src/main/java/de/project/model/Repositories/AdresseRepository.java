package de.project.model.Repositories;


import de.project.model.Entities.AdresseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends CrudRepository<AdresseEntity, Long> {
}
