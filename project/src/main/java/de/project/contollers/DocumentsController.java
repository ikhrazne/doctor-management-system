package de.project.contollers;

import de.project.model.Entities.DocumentEntity;
import de.project.services.FileStorageService;
import de.project.services.Mailing.MailingService;
import de.project.services.Mangers.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.NoSuchElementException;

// TODO: abstract this Functions to be used in others projects (documents managment)
@RestController
public class DocumentsController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MailingService mailingService;

    @PostMapping(path = "/document/upload/{appointementId}")
    public ResponseEntity<String> uploadDocument(@RequestHeader("Authorization") String token,
                                                 @RequestHeader("userId") Long userId,
                                                 @PathVariable("appointementId") Long appointementId,
                                                 @RequestParam("file")MultipartFile file) {

        if(SessionManager.isSessionValid(token, userId)) {
            try {
                String documentId = fileStorageService.saveFile(file, appointementId);
                return ResponseEntity.ok(documentId);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Session is not valid");
        }
    }

    @GetMapping(path = "/document/download/{documentId}")
    public ResponseEntity<Object> downloadDocument(@RequestHeader("Authorization") String token,
                                                 @RequestHeader("userId") Long userId,
                                                 @PathVariable("documentId") Long documentId) {

        if(SessionManager.isSessionValid(token, userId)) {
            try {
                DocumentEntity file = fileStorageService.getFile(documentId);
                return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + file.getDocumentName() + "\"").body(file.getData());
            } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
            }

        } else {
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(path = "/document/delete/{documentId}")
    public ResponseEntity<String> deleteDocument(@RequestHeader("Authorization") String token,
                                 @RequestHeader("userId") Long userId,
                                 @PathVariable("documentId") Long documentId) {

        if(SessionManager.isSessionValid(token, userId)) {
            return ResponseEntity.ok(fileStorageService.deleteFile(documentId));
        } else {
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "/images/upload")
    public String uploadImages(@RequestHeader("Authorization") String token,
                               @RequestHeader("userId") Long userId,
                               @RequestBody File image) {
        // TODO implement here
        return null;
    }
}
