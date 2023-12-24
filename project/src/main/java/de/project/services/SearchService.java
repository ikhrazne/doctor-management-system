package de.project.services;

import de.project.DTOs.Filters;
import de.project.DTOs.responseDTO.AppointementMetadata;
import de.project.Enums.Role;
import de.project.model.Entities.AppointmentEntity;
import de.project.model.Repositories.AppointmentRepository;
import de.project.model.Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SearchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<AppointmentEntity> unConvertedSearch(Filters filters, int startAt, int endAt) {

        if(filters.getInsurances().size() == 0 && filters.getInHowManyDays() == 0 && filters.getDoctorSpecialities().size() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorType(filters.getCity(), filters.getDoctorType(), startAt, endAt);
        } else if(filters.getInsurances().size() == 0 && filters.getInHowManyDays() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndDoctorSpecialities(filters.getCity(), filters.getDoctorType(), filters.getDoctorSpecialities(), startAt, endAt);
        } else if(filters.getInsurances().size() == 0 && filters.getDoctorSpecialities().size() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndInHowManyDays(filters.getCity(), filters.getDoctorType(), filters.getDate(), startAt, endAt);
        } else if(filters.getInHowManyDays() == 0 && filters.getDoctorSpecialities().size() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndInsurances(filters.getCity(), filters.getDoctorType(), filters.getInsurances(), startAt, endAt);
        } else if(filters.getInsurances().size() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndDoctorSpecialitiesAndInHowManyDays(filters.getCity(), filters.getDoctorType(), filters.getDoctorSpecialities(), filters.getDate(), startAt, endAt);
        } else if(filters.getInHowManyDays() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndDoctorSpecialitiesAndInsurances(filters.getCity(), filters.getDoctorType(), filters.getDoctorSpecialities(), filters.getInsurances(), startAt, endAt);
        } else if(filters.getDoctorSpecialities().size() == 0) {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndInsurancesAndInHowManyDays(filters.getCity(), filters.getDoctorType(), filters.getInsurances(), filters.getDate(), startAt, endAt);
        } else {
            return appointmentRepository.findDoctorsByCityAndDoctorTypeAndDoctorSpecialitiesAndInsurancesAndInHowManyDays(filters.getCity(), filters.getDoctorType(), filters.getDoctorSpecialities(), filters.getInsurances(), filters.getDate(), startAt, endAt);
        }
    }

    public List<AppointementMetadata> search(Filters filters, int startAt, int endAt) {
         return unConvertedSearch(filters, startAt, endAt).stream().map(appointmentEntity -> appointmentEntity.toMetadata(Role.PERSON)).collect(toList());
    }

}
