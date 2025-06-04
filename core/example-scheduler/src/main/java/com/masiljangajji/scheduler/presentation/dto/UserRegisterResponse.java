package com.masiljangajji.scheduler.presentation.dto;

import com.masiljangajji.scheduler.domain.User;

import java.util.UUID;

public record UserRegisterResponse(UUID id, String name, String status) {

    public static UserRegisterResponse from(User user) {
        return new UserRegisterResponse(user.getId(), user.getName(), user.getStatus());
    }

}
