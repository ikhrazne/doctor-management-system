package de.project.contollers;

import de.project.DTOs.requestDTO.AppointementRequestMetadata;
import de.project.DTOs.responseDTO.AppointementMetadata;
import de.project.Enums.Role;
import de.project.services.AppointementService;
import de.project.services.Mangers.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: abstract this Functions to be used in others projects (appoitements managment)
@RestController
public class AppoitementController {

    @Autowired
    private AppointementService appointementService;

    @PostMapping(path = "/appointements")
    public ResponseEntity<String> createAppointement(@RequestHeader("Authorization") String token,
                                                    @RequestHeader("userId") Long userId,
                                                    @RequestBody AppointementRequestMetadata appointementRequestMetadata) {
        if(SessionManager.isSessionValid(token, userId)){
             return new ResponseEntity<>(appointementService.createAppointement(appointementRequestMetadata), null, 200);
        } else {
            return new ResponseEntity<>("the session is not valid", null, 401);
        }

    }

    @PostMapping(path = "/appointements/many")
    public ResponseEntity<String> createManyAppointements(@RequestHeader("Authorization") String token,
                                          @RequestHeader("userId") Long userId,
                                          @RequestBody List<AppointementRequestMetadata> appointementRequestMetadataList){
        if(SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(appointementService.createManyAppointements(appointementRequestMetadataList), null, 200);
        } else {
            return new ResponseEntity<>("the session is not valid", null, 401);
        }
    }

    @GetMapping(path = "/appointements/{doctorId}")
    public ResponseEntity<List<AppointementMetadata>> appointementsOfDoctor(@RequestHeader("Authorization") String token,
                                                         @RequestHeader("userId") Long userId,
                                                         @PathVariable("doctorId") Long doctorId,
                                                         @RequestParam("startAt") int startAt,
                                                         @RequestParam("endAt") int endAt){
        if(SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(appointementService.getDoctorAppointments(doctorId, startAt, endAt), null, 200);
        } else {
            return new ResponseEntity<>(null, null, 401);
        }
    }

    @PostMapping(path = "/appointements/{id}")
    public ResponseEntity<String> bookAppointement(@RequestHeader("Authorization") String token,
                                   @RequestHeader("userId") Long userId,
                                   @PathVariable("id") Long appointementId){
        if(SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(appointementService.reserveAppointement(appointementId, userId), null, 200);
        } else {
            return new ResponseEntity<>("the session is not valid", null, 401);
        }
    }

    @DeleteMapping(path = "/appointements/{appointmentId}")
    public ResponseEntity<String> stornierAppointement(@RequestHeader("Authorization") String token,
                                                         @RequestHeader("userId") Long userId,
                                                         @PathVariable("appointmentId") Long appointmentId){
        if (SessionManager.isSessionValid(token, userId)) {
            return new ResponseEntity<>(appointementService.storniereAppointement(appointmentId, userId), null, 200);
        } else {
            return new ResponseEntity<>("the session is not valid", null, 401);
        }
    }

    @GetMapping(path = "/appointements")
    public ResponseEntity<List<AppointementMetadata>> getMyAppointements(@RequestHeader("Authorization") String token,
                                                                @RequestHeader("userId") Long userId,
                                                                @RequestHeader("role") Role role,
                                                                @RequestParam("startAt") int startAt,
                                                                @RequestParam("endAt") int endAt) {
        if(SessionManager.isSessionValid(token, userId)){
            return new ResponseEntity<>(appointementService.getMyAppointements(userId, role, startAt, endAt), null, 200);
        } else {
            return new ResponseEntity<>(null, null, 401);
        }
    }

}
