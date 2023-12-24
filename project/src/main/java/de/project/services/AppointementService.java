package de.project.services;


import de.project.DTOs.requestDTO.AppointementRequestMetadata;
import de.project.DTOs.responseDTO.AppointementMetadata;
import de.project.Enums.Role;
import de.project.model.Entities.AppointmentEntity;
import de.project.model.Entities.UserEntity;
import de.project.model.Repositories.AppointmentRepository;
import de.project.model.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
public class AppointementService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    public String createAppointement(AppointementRequestMetadata appointementRequestMetadata) {
        AppointmentEntity appointement = appointementRequestMetadata.toEntity();
        try {
            UserEntity doctor = userRepository.findById(appointementRequestMetadata.getDoctorId()).get();
            System.out.println(doctor.toString());
            appointement.setDoctor(doctor);
        } catch (NoSuchElementException e) {
            return "the doctor doesn't exist in the database";
        }
        appointmentRepository.save(appointement);
        return "The appointement has been created successfully";
    }

    @Transactional
    public String createManyAppointements(List<AppointementRequestMetadata> appointementRequestMetadataList) {
        for(AppointementRequestMetadata appointementRequestMetadata : appointementRequestMetadataList) {
            String created = createAppointement(appointementRequestMetadata);
        }
        return "The appointements have been created successfully";
    }

    @Transactional
    public String reserveAppointement(Long appointementId, Long userId) {
        AppointmentEntity appointement = appointmentRepository.findById(appointementId).isPresent() ? appointmentRepository.findById(appointementId).get() : null;

        if(appointement == null) {
            return "The appointement does not exist";
        }

        UserEntity user = userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() : null;

        if(user == null) {
            return "The user does not exist";
        }

        appointement.setPatient(user);

        appointmentRepository.save(appointement);

        return "reserved successfully";
    }

    @Transactional
    public String storniereAppointement(Long appointmentId, Long userId) {
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId).isPresent() ? appointmentRepository.findById(appointmentId).get() : null;

        if(appointment == null) {
            return "The appointement does not exist";
        }

        if(Objects.equals(appointment.getPatient().getUserId(), userId)) {
            appointment.setPatient(null);
            appointmentRepository.save(appointment);
            return "The appointement has been storniert successfully";
        }
        else {
            return "The appointement is not reserved by this user";
        }
    }


    public Boolean authomaticGenerateAppointement(int timeSlot, int maxAppointementPerDay) {
        // TODO: generate appointement based on the time slot choosen by doctor and the max number of appointements in the day
        // TODO: schedule this job to be used every 7 days
        // TODO: Save this appointement in a inmemory database or in database to track the change of the appointement
        return null;
    }

    public List<AppointementMetadata> getMyAppointements(Long userId, Role role, int startAt, int endAt) {
        return appointmentRepository.findAllByUserId(userId, startAt, endAt).stream().map(appointmentEntity -> appointmentEntity.toMetadata(role)).toList();
    }

    public List<AppointementMetadata> getDoctorAppointments(Long doctorId, int startAt, int endAt) {
        return userRepository.findDoctorsAppointement(doctorId, startAt, endAt).stream().map(appointmentEntity -> appointmentEntity.toMetadata(Role.PERSON)).toList();
    }

}
