package com.masiljangajji.scheduler.presentation.dto;

import com.masiljangajji.scheduler.domain.User;

import java.util.UUID;

public record UserCreateResponse(UUID id, String name) {

    public static UserCreateResponse from(User user) {
        return new UserCreateResponse(user.getId(), user.getName());
    }

}
