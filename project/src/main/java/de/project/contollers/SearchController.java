package de.project.contollers;


import de.project.DTOs.AppointementInfo;
import de.project.DTOs.Filters;
import de.project.DTOs.responseDTO.AppointementMetadata;
import de.project.model.Entities.AppointmentEntity;
import de.project.services.Mangers.SessionManager;
import de.project.services.SearchService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(path= "/search")
    public ResponseEntity<List<AppointementMetadata>> search(@RequestHeader("Authorization") String token,
                                                             @RequestHeader("userId") Long userId,
                                                             @RequestHeader("role") String role,
                                                             @RequestBody Filters filters,
                                                             @PathParam("startAt") int startAt,
                                                             @PathParam("endAt") int endAt) {

        if(SessionManager.isSessionValid(token, userId) && role.equals("PERSON")) {
            return new ResponseEntity<>(searchService.search(filters, startAt, endAt), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

}
