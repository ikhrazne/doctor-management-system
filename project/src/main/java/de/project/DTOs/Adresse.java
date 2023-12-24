package de.project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {

    private String street;
    private int streetNumber;
    private String city;
    private int plz;
    private String country;
}
