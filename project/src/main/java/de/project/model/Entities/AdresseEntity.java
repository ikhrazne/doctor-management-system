package de.project.model.Entities;


import de.project.DTOs.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "adresse")
public class AdresseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adresseId;

    @Column
    private String street;

    @Column
    private int streetNumber;

    @Column
    private String city;

    @Column
    private int plz;

    @Column
    private String country;

    @OneToOne(mappedBy = "adresse")
    private UserEntity user;

    protected AdresseEntity() {

    }

    public AdresseEntity(String street, int streetNumber, String city, int plz, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.plz = plz;
        this.country = country;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
