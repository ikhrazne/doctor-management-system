package de.project.model.Entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor_type")
public class DoctorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorTypeId;

    @Column
    private String doctorType;

    @OneToMany(mappedBy = "doctorType")
    private List<UserEntity> doctors;

    protected DoctorType() {}

    public DoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public Long getDoctorTypeId() {
        return doctorTypeId;
    }

    public void setDoctorTypeId(Long doctorTypeId) {
        this.doctorTypeId = doctorTypeId;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public List<UserEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<UserEntity> doctors) {
        this.doctors = doctors;
    }
}
