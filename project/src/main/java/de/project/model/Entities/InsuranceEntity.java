package de.project.model.Entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "insurance")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insuranceId;

    @Column
    private String insuranceName;

    @ManyToMany(mappedBy = "insurances")
    private Set<UserEntity> doctors;

    protected InsuranceEntity() {

    }

    public InsuranceEntity(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
}
