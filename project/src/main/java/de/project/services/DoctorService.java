package de.project.services;


import de.project.model.Entities.UserEntity;
import de.project.model.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllDoctors() {
        return userRepository.findAllDoctors();
    }

    public UserEntity getDoctorByName(String firstName, String lasName) {
        return userRepository.findDoctorByName(firstName, lasName);
    }
}
