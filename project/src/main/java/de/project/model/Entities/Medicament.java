package de.project.model.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicament_id;

    @Column
    private String medicament;

    protected Medicament() {}

    public Medicament(String medicament) {
        this.medicament = medicament;
    }

    public Long getMedicament_id() {
        return medicament_id;
    }

    public void setMedicament_id(Long medicament_id) {
        this.medicament_id = medicament_id;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }
}
