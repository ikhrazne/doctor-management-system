package de.project.DTOs.responseDTO;

import de.project.DTOs.Adresse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {

    private String firstName;
    private String secondName;
    private String doctorType;
    private String description;
    private Adresse adresse;
    private Set<String> specialities;
    private List<String> spokenLanguages;
    private List<String> insurances;
    private String phoneNumber;
    private String email;
    private List<String> openingHours;
    private Byte[] pictures;
}
