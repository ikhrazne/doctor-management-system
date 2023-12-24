package de.project.services.Mangers.Model;

import de.project.Enums.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Session {

    private Long userId;
    private String token;
    private LocalDateTime expirationTime;
    private LocalDateTime createdAt;
    private Role role;

}
