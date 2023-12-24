package de.project.model.Repositories;


import de.project.model.Entities.DoctorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorTypeRepository extends JpaRepository<DoctorType, Long> {
}
