package de.project.services.Mangers;

import de.project.Enums.Role;
import de.project.services.Mangers.Model.Session;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionManager {

    final static long sessionValidationTime = 10;
    static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
    static ScheduledExecutorService executorService = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();

    public static Map<String, Session> getSessionsMap() {
        return sessionsMap;
    }

    public static String createSession(Long userId, String token, Role role) {
        Session session = new Session();
        session.setUserId(userId);
        session.setToken(token);
        session.setExpirationTime(LocalDateTime.now().plusMinutes(sessionValidationTime));
        session.setCreatedAt(LocalDateTime.now());
        session.setRole(role);

        sessionsMap.put(token, session);
        executorService.schedule(() -> {
            sessionsMap.remove(token);
        }, 2, TimeUnit.MINUTES);

        return token;
    }

    public static String deleteSession(String token) {
        sessionsMap.remove(token);
        return "Session deleted";
    }

    public static boolean isSessionValid(String token, Long userId) {
        return sessionsMap.containsKey(token) && sessionsMap.get(token).getUserId().equals(userId);
    }

    public static String refreshSession(String oldToken, String newToken, Long userId) {
        Role role = sessionsMap.get(oldToken).getRole();
        deleteSession(oldToken);
        return createSession(userId, newToken, role);
    }

}
