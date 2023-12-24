package de.project.DTOs.requestDTO;


import de.project.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
