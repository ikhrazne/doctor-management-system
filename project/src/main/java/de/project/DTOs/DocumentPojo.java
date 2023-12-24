package de.project.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentPojo {

    private String firstName;
    private String lastName;
    private Adresse adresse;
    private String email;
    private String phoneNumber;
    private String insurance;
    private String notizen;
}
