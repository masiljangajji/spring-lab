package com.masiljangajji.scheduler.persistence;

import com.masiljangajji.scheduler.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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

}
