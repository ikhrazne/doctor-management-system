package de.project.services;


import de.project.model.Entities.AppointmentEntity;
import de.project.model.Entities.DocumentEntity;
import de.project.model.Repositories.AppointmentRepository;
import de.project.model.Repositories.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FileStorageService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public String saveFile(MultipartFile file, Long appointmentId) throws IOException {
        if (file.isEmpty()) {
            return "Cannot upload empty file";
        }

        if (file.getSize() > 10000) {
            return "File is too big";
        }

        AppointmentEntity appointment;
        try {
            appointment = appointmentRepository.findById(appointmentId).get();
        } catch (Exception e) {
            return "Appointment does not exist";
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        DocumentEntity document = new DocumentEntity(fileName, LocalDateTime.now(), file.getContentType(), file.getBytes());

        document.setAppointment(appointment);

        return documentRepository.save(document).getDocument_id().toString();
    }

    public DocumentEntity getFile(Long id) {
        try {
            return documentRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public String deleteFile(Long id) {
        if(!documentRepository.existsById(id)) {
            return "File does not exist";
        }

        documentRepository.deleteById(id);
        return "File deleted successfully";
    }

    @Transactional
    public Long updateFile(Long id, MultipartFile file) {

        if(file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        if(file.getSize() > 10000 ) {
            throw new IllegalStateException("File is too big");
        }

        try {
            DocumentEntity document = documentRepository.findById(id).get();
            document.setDocumentName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            document.setCreatedAt(LocalDateTime.now());
            document.setFileExtension(file.getContentType());
            document.setData(file.getBytes());
            return documentRepository.save(document).getDocument_id();
        } catch (Exception e) {
            return null;
        }
    }

}
