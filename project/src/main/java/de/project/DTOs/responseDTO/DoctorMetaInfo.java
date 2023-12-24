package de.project.DTOs.responseDTO;

import de.project.DTOs.Adresse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorMetaInfo {

    private String firstName;
    private String secondName;
    private String doctorType;
    private Adresse adresse;

}
