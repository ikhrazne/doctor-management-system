package de.project.services;

import de.project.DTOs.User;
import de.project.DTOs.UserInformationDTO;
import de.project.model.Entities.UserEntity;
import de.project.model.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll().stream().map(UserEntity::convertToUser).collect(Collectors.toList());
    }

    public User getUserById(Long userId) {
        UserEntity user = userRepo.findById(userId).orElse(null);
        if(user == null){
            return null;
        }
        return user.convertToUser();
    }

    @Transactional
    public Long createUser(UserEntity userEntity) {
        userEntity.setPassword(userEntity.getPassword());
        UserEntity userEntity1 = userRepo.save(userEntity);
        return userEntity1.getUserId();
    }

    public Boolean deleteUserByUserId(Long userId) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        userRepo.deleteById(userId);
        return Boolean.TRUE;
    }

    @Transactional
    public User updateUserByUserId(Long userId, User user) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        assert userEntity != null;
        userEntity.generateFromUser(user);

        userRepo.save(userEntity);

        return user;
    }

    public UserInformationDTO getUserInformationByUserId(Long userId) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        assert userEntity != null;
        return userEntity.getUserInformation().convertToDTO();
    }

    @Transactional
    public Boolean saveUserInformationByUserId(Long userId, UserInformationDTO userInformationDTO) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        assert userEntity != null;
        userEntity.setUserInformation(userInformationDTO.convertToUserInformation());
        userRepo.save(userEntity);
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean updateUserInformationByUserId(Long userId, UserInformationDTO userInformationDTO) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        assert userEntity != null;
        userEntity.setUserInformation(userInformationDTO.convertToUserInformation());
        userRepo.save(userEntity);
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean deleteAllUsers() {
        userRepo.deleteAll();
        return Boolean.TRUE;
    }

}
