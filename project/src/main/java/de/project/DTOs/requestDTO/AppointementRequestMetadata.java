package de.project.DTOs.requestDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.project.model.Entities.AppointmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class AppointementRequestMetadata {

    @JsonProperty("doctorId")
    private Long doctorId;

    @JsonProperty("date")
    private String dateInString;

    @JsonIgnore
    private LocalDateTime date;

    public AppointementRequestMetadata(Long doctorId, String dateInString) {
        this.doctorId = doctorId;
        this.dateInString = dateInString;
    }

    public AppointmentEntity toEntity() {
        this.date = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return new AppointmentEntity(this.date);
    }

}
