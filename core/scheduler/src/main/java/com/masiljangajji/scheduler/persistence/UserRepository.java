package com.masiljangajji.scheduler.persistence;

import com.masiljangajji.scheduler.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    List<User> findAll();

    User findById(UUID id);

    User insert(User user);

}
