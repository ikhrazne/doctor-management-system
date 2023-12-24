package de.project.DTOs.responseDTO;

import de.project.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointementMetadata {

    private Long id;
    private String Firstname;
    private String Lastname;
    private Object doctorType;
    private LocalDateTime date;
    private Role role;

    public AppointementMetadata(Long id, String Firstname, String Lastname,LocalDateTime date, Role role) {
        this.id = id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.date = date;
        this.role = role;
    }

}
