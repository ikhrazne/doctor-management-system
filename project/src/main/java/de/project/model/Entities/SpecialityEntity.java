package de.project.model.Entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "speciality")
public class SpecialityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speciality_id;

    @Column
    private String speciality;

    @ManyToMany(mappedBy = "specialities")
    private Set<UserEntity> doctors;

    protected SpecialityEntity() {}

    public SpecialityEntity(String speciality) {
        this.speciality = speciality;
    }

    public Long getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(Long speciality_id) {
        this.speciality_id = speciality_id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Set<UserEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<UserEntity> doctors) {
        this.doctors = doctors;
    }
}
