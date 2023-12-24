package de.project.model.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import de.project.DTOs.requestDTO.AppointementRequestMetadata;
import de.project.DTOs.responseDTO.AppointementMetadata;
import de.project.Enums.Role;
import de.project.converters.LocalDateTimeConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "appointement")
public class AppointmentEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    private Long appointmentId;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "doctor_user_id")
    private UserEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_user_id")
    private UserEntity patient;

    @OneToMany
    @JoinColumn(name = "appointment_id")
    private Set<DocumentEntity> documentEntitySet;

    protected AppointmentEntity() {

    }

    public AppointmentEntity(LocalDateTime date) {
        this.date = date;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    public UserEntity getPatient() {
        return patient;
    }

    public void setPatient(UserEntity patient) {
        this.patient = patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<DocumentEntity> getDocumentEntitySet() {
        return documentEntitySet;
    }

    public void setDocumentEntitySet(Set<DocumentEntity> documentEntitySet) {
        this.documentEntitySet = documentEntitySet;
    }

    public AppointementMetadata toMetadata(Role role) {
        if(role.equals(Role.DOCTOR)) {
            return new AppointementMetadata(this.appointmentId, this.doctor.getFirstName(), this.doctor.getLastName(), this.doctor.getDoctorType(), this.date, this.doctor.getRole());
        }
        else if(role.equals(Role.PERSON)) {
            return new AppointementMetadata(this.appointmentId, this.doctor.getFirstName(), this.doctor.getLastName(), this.date, this.patient.getRole());
        }
        else {
            return null;
        }
    }


}
