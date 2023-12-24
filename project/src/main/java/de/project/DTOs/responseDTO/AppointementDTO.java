package de.project.DTOs.responseDTO;

import de.project.DTOs.Adresse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointementDTO {

    private String doctorFirstname;
    private String doctorLastname;
    private String doctorType;
    private LocalDateTime date;
    private Adresse adresse;
    private String phoneNumber;
    private List<String> insurances;

}
