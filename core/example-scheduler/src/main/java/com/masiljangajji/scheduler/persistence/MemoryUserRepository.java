package com.masiljangajji.scheduler.persistence;

import com.masiljangajji.scheduler.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
@AllArgsConstructor
public class MemoryUserRepository implements UserRepository {

    private final Map<UUID, User> db = new ConcurrentHashMap<>();

    @Override
    public List<User> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public User findById(UUID id) {
        return db.get(id);
    }

    @Override
    public User insert(User user) {
        return db.computeIfAbsent(user.getId(), k -> user);
    }

    @Override
    public void deleteUserCreatedBefore(LocalDateTime cutoff) {

        db.values().removeIf(user -> {

            boolean expired = user.getCreatedAt().isBefore(cutoff);

            if (expired) {
                log.info("User id {} deleted - createdAt = {}", user.getId(), user.getCreatedAt());
            }

            return expired;
        });
    }

}
