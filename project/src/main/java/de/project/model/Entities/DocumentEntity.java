package de.project.model.Entities;

import de.project.converters.LocalDateTimeConverter;
import jakarta.persistence.*;

import java.io.File;
import java.time.LocalDateTime;


@Entity
@Table(name = "document")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long document_id;

    @Column
    private String documentName;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @Column
    private String fileExtension;

    @Lob
    private byte[] data;

    @ManyToOne
    private AppointmentEntity appointment;

    /* @ManyToOne
    @JoinColumn(name = "doctor_user_id")
    private UserEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_user_id")
    private UserEntity patient; */

    protected DocumentEntity() {}

    public DocumentEntity(String documentName, LocalDateTime createdAt, String fileExtension, byte[] data) {
        this.documentName = documentName;
        this.createdAt = createdAt;
        this.fileExtension = fileExtension;
        this.data = data;
    }

    public Long getDocument_id() {
        return document_id;
    }

    public void setDocument_id(Long document_id) {
        this.document_id = document_id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }
}
