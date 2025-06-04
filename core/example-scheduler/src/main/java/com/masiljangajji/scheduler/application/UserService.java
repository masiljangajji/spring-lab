package com.masiljangajji.scheduler.application;

import com.masiljangajji.scheduler.domain.User;
import com.masiljangajji.scheduler.domain.LocalUserDeletePolicy;
import com.masiljangajji.scheduler.persistence.MemoryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemoryUserRepository userRepository;

    private final LocalUserDeletePolicy localUserDeletePolicy;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(UUID id) {
        User user = userRepository.findById(id);
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

    public User insertUser(String name) {
        User user = User.of(name);
        return userRepository.insert(user);
    }

    public void deleteUserByPolicy() {
        userRepository.deleteUserCreatedBefore(localUserDeletePolicy.getCutoff());
    }

}
