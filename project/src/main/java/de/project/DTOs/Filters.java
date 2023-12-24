package de.project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Filters {

    private String doctorType;
    private String city;
    private List<String> insurances;
    private List<String> doctorSpecialities;
    private LocalDateTime date;
    private int inHowManyDays;

    public Filters(String doctorType, String city, List<String> insurances, List<String> doctorSpecialities, int inHowManyDays) {
        this.doctorType = doctorType;
        this.city = city;
        this.insurances = insurances;
        this.doctorSpecialities = doctorSpecialities;
        this.inHowManyDays = inHowManyDays;
        this.date = this.date.plusDays(inHowManyDays);
    }

}
