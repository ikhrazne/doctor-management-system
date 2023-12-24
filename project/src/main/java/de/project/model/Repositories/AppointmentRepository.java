package de.project.model.Repositories;

import de.project.model.Entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("select a from AppointmentEntity a where a.doctor.userId = ?1 or a.patient.userId = ?1")
    List<AppointmentEntity> findAllByUserId(Long userId,
                                            int startAt,
                                            int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a WHERE a.doctor.adresse.city = :city AND a.doctor.doctorType = :doctorType order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorType(String city,
                                                           String doctorType,
                                                           int startAt,
                                                           int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a join a.doctor d join d.specialities s WHERE d.adresse.city = :city AND d.doctorType.doctorType = :doctorType AND s.speciality IN :doctorSpecialitiesby order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndDoctorSpecialities(String city,
                                                                                String doctorType,
                                                                                List<String> doctorSpecialities,
                                                                                int startAt,
                                                                                int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a WHERE a.doctor.adresse.city = :city AND a.doctor.doctorType = :doctorType AND a.date BETWEEN CURRENT_DATE AND :date order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndInHowManyDays(String city,
                                                                           String doctorType,
                                                                           LocalDateTime date,
                                                                           int startAt,
                                                                           int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a join a.doctor d join d.insurances i WHERE d.adresse.city = :city AND d.doctorType.doctorType = :doctorType AND i.insuranceName IN :insurances order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndInsurances(String city,
                                                                        String doctorType,
                                                                        List<String> insurances,
                                                                        int startAt,
                                                                        int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a join a.doctor d join d.specialities s WHERE d.adresse.city = :city AND d.doctorType = :doctorType AND s.speciality IN :doctorSpecialities AND a.date BETWEEN CURRENT_DATE AND :date order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndDoctorSpecialitiesAndInHowManyDays(String city,
                                                                                                String doctorType,
                                                                                                List<String> doctorSpecialities,
                                                                                                LocalDateTime date,
                                                                                                int startAt,
                                                                                                int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a join a.doctor d join d.specialities s join d.insurances i WHERE d.adresse.city = :city AND d.doctorType.doctorType = :doctorType AND i.insuranceName IN :insurances AND s.speciality IN :doctorSpecialities order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndDoctorSpecialitiesAndInsurances(String city,
                                                                                             String doctorType,
                                                                                             List<String> doctorSpecialities,
                                                                                             List<String> insurances,
                                                                                             int startAt,
                                                                                             int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a join a.doctor d join d.insurances i WHERE d.adresse.city = :city AND d.doctorType.doctorType = :doctorType AND i.insuranceName IN :insurances  And a.date BETWEEN CURRENT_DATE AND :date order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndInsurancesAndInHowManyDays(String city,
                                                                                        String doctorType,
                                                                                        List<String> insurances,
                                                                                        LocalDateTime date,
                                                                                        int startAt,
                                                                                        int endAt);

    @Query(value = "SELECT a FROM AppointmentEntity a join a.doctor d join d.insurances i join d.specialities s WHERE d.adresse.city = :city AND d.doctorType.doctorType = :doctorType AND i.insuranceName IN :insurances AND s.speciality IN :doctorSpecialities AND a.date BETWEEN CURRENT_DATE AND :date order by a.date ASC offset :startAt limit :endAt", nativeQuery = true)
    List<AppointmentEntity> findDoctorsByCityAndDoctorTypeAndDoctorSpecialitiesAndInsurancesAndInHowManyDays(String city,
                                                                                                             String doctorType,
                                                                                                             List<String> doctorSpecialities,
                                                                                                             List<String> insurances,
                                                                                                             LocalDateTime date,
                                                                                                             int startAt,
                                                                                                             int endAt);

}
