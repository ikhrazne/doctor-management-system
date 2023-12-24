package de.project.contollers;


import de.project.DTOs.User;
import de.project.DTOs.responseDTO.MessageResponse;
import de.project.model.Entities.UserEntity;
import de.project.services.Mailing.MailingService;
import de.project.services.Mangers.SessionManager;
import de.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailingService mailingService;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token, @RequestHeader("userId") Long userId) {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path ="/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token) {
        if(!SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Map<String, Long>> createUser(@RequestBody UserEntity userEntity,
                                                        @RequestHeader("userId") Long userId,
                                                        @RequestHeader("Authorization") String token) {

        if(!SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Map<String, Long> map = new HashMap<>();
        map.put("userId", userService.createUser(userEntity));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<User> updateUserByUserId(@PathVariable Long userId, @RequestBody User user, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(userService.updateUserByUserId(userId, user), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<MessageResponse> deleteUserByUserId(@PathVariable Long userId, @RequestHeader("Authorization") String token){
        if(!SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(new MessageResponse("Unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        Boolean updated = userService.deleteUserByUserId(userId);
        if(!updated){
            return new ResponseEntity<>(new MessageResponse("User with id " + userId + " was not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MessageResponse("User with id " + userId + " was deleted"), HttpStatus.OK);
    }

    // TODO: For testing purposes only
    @DeleteMapping(path = "/users")
    public ResponseEntity<MessageResponse> deleteAllUsers(@RequestHeader("Authorization") String token) {
        if(!SessionManager.isSessionValid(token, 1L)){
            return new ResponseEntity<>(new MessageResponse("Unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        userService.deleteAllUsers();
        return new ResponseEntity<>(new MessageResponse("All users were deleted"), HttpStatus.OK);
    }

}
