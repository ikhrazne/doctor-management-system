package de.project.model.Repositories;

import de.project.model.Entities.AppointmentEntity;
import de.project.model.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.role = 'DOCTOR'")
    List<UserEntity> findAllDoctors();

    @Query("SELECT u FROM UserEntity u WHERE u.firstName = ?1 AND u.lastName = ?2 AND u.role = 'DOCTOR'")
    UserEntity findDoctorByName(String firstName, String lastName);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);

    @Query(value = "SELECT u.appointments from UserEntity WHERE u.userId = :userId offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findAllByUserId(@Param("userId") Long userId,
                                            @Param("startAt") int startAt,
                                            @Param("endAt") int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a WHERE a.doctor.userId = ?1 and a.patient = null order by a.date ASC offset ?2 limit ?2", nativeQuery = true)
    List<AppointmentEntity> findDoctorsAppointement(Long doctorId,
                                                    int startAt,
                                                    int endAt);

}
