package de.project.DTOs;

import de.project.model.Entities.AdresseEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointementInfo {

    private String doctorName;
    private LocalDate date;
    private AdresseEntity adresse;
    private String doctorType;

}
