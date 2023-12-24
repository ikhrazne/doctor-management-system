package de.project.services;

import de.project.model.Entities.UserEntity;
import de.project.model.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepo;


    static Map<String, Long> forgetPasswordMap = new ConcurrentHashMap<>();
    static ScheduledExecutorService executorService;

    public Map<Long, String> login(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserEntity userEntity = userRepo.findByEmail(email);
        Map<Long, String> sessionInfo = new HashMap<>();
        if(userEntity == null){
            sessionInfo.put(0L, "User not found");
            return sessionInfo;
        }
        if(!userEntity.getPassword().equals(password)){
            sessionInfo.put(0L, "Wrong password");
            return sessionInfo;
        }

        UUID uuid = UUID.randomUUID();
        sessionInfo.put(userEntity.getUserId(), uuid.toString());
        return sessionInfo;
    }

    public UUID forgetPassword(String email) {
        UserEntity userEntity = userRepo.findByEmail(email);

        if(userEntity == null){
            return null;
        }
        UUID uuid = UUID.randomUUID();
        forgetPasswordMap.put(String.valueOf(uuid), userEntity.getUserId());
        executorService.schedule(() -> {
            forgetPasswordMap.remove(String.valueOf(uuid));
        }, 2, TimeUnit.MINUTES);

        return uuid;
    }

    @Transactional
    public Long register(UserEntity userEntity) {
        UserEntity user = userRepo.findByEmail(userEntity.getEmail());
        if(user != null) {
            return null;
        }
        userEntity.setPassword(userEntity.getPassword());
        UserEntity userEntity1 = userRepo.save(userEntity);
        return userEntity1.getUserId();
    }

    @Transactional
    public String changePassword(Long userId, String password) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        if(userEntity == null){
            return "User not found";
        }
        userEntity.setPassword(password);
        userRepo.save(userEntity);
        return "Password updated";
    }
}
