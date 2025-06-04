package com.masiljangajji.scheduler.application;

import com.masiljangajji.scheduler.domain.User;
import com.masiljangajji.scheduler.domain.UserDeletePolicy;
import com.masiljangajji.scheduler.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

//@Transectional 존재한다고 가정
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserDeletePolicy userDeletePolicy;

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
        userRepository.deleteUserCreatedBefore(userDeletePolicy.getCutoff());
    }

    public boolean deleteUserByPolicyWithTryCatch() {

        try {
            userRepository.deleteUserCreatedBefore(userDeletePolicy.getCutoff());
        } catch (Exception e) {
            log.error("failed deleteUserCreatedBeforeWithTryCatch - {}", e.getMessage(), e);
            return true;
        }
        return false;
    }

    public void deleteUserByPolicyWithIds(List<UUID> ids) {
        userRepository.deleteUserByIds(ids);
    }

}
