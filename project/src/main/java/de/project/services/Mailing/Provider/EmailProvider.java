package de.project.services.Mailing.Provider;

import de.project.DTOs.Email;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailProvider {


    public EmailProvider() {
    }

    public Email buildEmailBody(String to, String subject, String body) throws InvalidKeySpecException, NoSuchAlgorithmException {
            return new Email(to, subject, body);
    }

    public Email buildRegistrationEmailBody(Long userId, String firstname, String lastname, String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        
        return new Email();
    }


}
