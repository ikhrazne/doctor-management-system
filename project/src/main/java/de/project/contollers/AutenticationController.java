package de.project.contollers;


import de.project.DTOs.requestDTO.UserLogin;
import de.project.DTOs.requestDTO.UserRegistration;
import de.project.DTOs.responseDTO.AuthToken;
import de.project.DTOs.responseDTO.MessageResponse;
import de.project.Enums.Role;
import de.project.model.Entities.UserEntity;
import de.project.services.AuthenticationService;
import de.project.services.Mailing.MailingService;
import de.project.DTOs.Email;
import de.project.services.Mailing.Provider.EmailProvider;
import de.project.services.Mangers.Model.Session;
import de.project.services.Mangers.SessionManager;
import de.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
public class AutenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailingService mailingService;


    @PostMapping(path = "/login")
    public ResponseEntity<AuthToken> login(@RequestBody UserLogin userLogin) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Map<Long, String> sessionInfo= authenticationService.login(userLogin.getEmail(), userLogin.getPassword());

        if(sessionInfo.keySet().toArray()[0].equals(0L)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Long userId = Long.valueOf(sessionInfo.keySet().toArray()[0].toString());
        Role role = userService.getUserById(userId).getRole();
        SessionManager.createSession(userId, sessionInfo.get(userId), role);
        return new ResponseEntity<>(new AuthToken(sessionInfo.get(userId)), HttpStatus.OK);
    }

    @PostMapping(path = "/logout")
    public String logout(@RequestHeader("Authorization") String token){
        return SessionManager.deleteSession(token);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<MessageResponse> register(@RequestBody UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserEntity user = new UserEntity(userRegistration.getFirstName(), userRegistration.getLastName(), userRegistration.getEmail(), userRegistration.getPassword(), userRegistration.getRole());
        Long userId = authenticationService.register(user);

        if(userId == null) {
            return new ResponseEntity<>(new MessageResponse("User with email " + userRegistration.getEmail() + " already exists"), HttpStatus.CONFLICT);
        }
        EmailProvider emailProvider = new EmailProvider();
        Email email = emailProvider.buildRegistrationEmailBody(user.getUserId(), userRegistration.getFirstName(), userRegistration.getLastName(), userRegistration.getEmail());

        return new ResponseEntity<>(new MessageResponse(mailingService.sendSimpleMail(email)), HttpStatus.OK);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<AuthToken> refresh(@RequestHeader("Authorization") String token,
                                             @RequestHeader("userId") Long userId) {
        if(!SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UUID uuid = UUID.randomUUID();
        return new ResponseEntity<>(new AuthToken(SessionManager.refreshSession(token, uuid.toString(), userId)), HttpStatus.OK);
    }

    @PostMapping(path = "/users/{userId}/password")
    public ResponseEntity<MessageResponse> changePassword(@PathVariable Long userId, @RequestBody String password) {
        String updated = authenticationService.changePassword(userId, password);
        if(Objects.equals(updated, "User not found")){
            return new ResponseEntity<>(new MessageResponse("User with id " + userId + " was not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MessageResponse("the possword of User with id " + userId + " was updated"), HttpStatus.OK);
    }

    @PostMapping(path = "/users/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody String email) {
        UUID forgetLink = authenticationService.forgetPassword(email);

        if(forgetLink == null) {
            return new ResponseEntity<>("User with email " + email + " was not found", HttpStatus.NOT_FOUND);
        }

        // EmailProvider emailProvider = new EmailProvider();
        // Email mail = emailProvider.buildRegistrationEmailBody(user.getUserId(), userRegistration.getFirstName(), userRegistration.getLastName(), userRegistration.getEmail());

        Email mail = new Email();
        return new ResponseEntity<>(mailingService.sendSimpleMail(mail), HttpStatus.OK);
    }

    // TODO: for testing purposes, implement session functions
    @GetMapping(path = "/sessions")
    public Map<String, Session> getSessions() {
        return SessionManager.getSessionsMap();
    }
}
