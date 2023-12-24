package de.project.services.Mangers;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LinksTokenManager {

    final static long timeForLinkExpiration = 2;
    static Map<String, Long> cache = new ConcurrentHashMap<>();
    static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);


    public void createToken(Long userId) {
        String token = generateToken();
        cache.put(token, userId);
        executorService.schedule(() -> {
            cache.remove(token);
        }, timeForLinkExpiration, TimeUnit.MINUTES);
    }

    public String generateToken() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    public boolean deleteToken(String token) {
        if(cache.containsKey(token)){
            cache.remove(token);
            return true;
        }
        return false;
    }

    public boolean checkToken(String token) {
        return cache.containsKey(token);
    }

    public Long getUserByToken(String token) {
        if(checkToken(token))
        {
            return cache.get(token);
        }
        return null;
    }
}
