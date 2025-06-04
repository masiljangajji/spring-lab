package com.masiljangajji.scheduler.persistence;

import com.masiljangajji.scheduler.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface UserRepository {

    List<User> findAll();

    List<UUID> findAllUserIdsByPolicy(LocalDateTime cutoff);

    User findById(UUID id);

    User insert(User user);

    void deleteUserCreatedBefore(LocalDateTime cutoff);

    void deleteUserByIds(List<UUID> ids);

}
