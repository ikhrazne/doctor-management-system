package de.project.DTOs;


import de.project.Enums.Role;
import lombok.Data;

@Data
public class User {

    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Role role;

}
