package com.masiljangajji.scheduler.presentation.dto;

import com.masiljangajji.scheduler.domain.User;

import java.util.List;
import java.util.UUID;

public record UserGetResponse(UUID id, String name, String status) {

    public static UserGetResponse from(User user) {
        return new UserGetResponse(user.getId(), user.getName(), user.getStatus());
    }

    public static List<UserGetResponse> from(List<User> users) {
        return users.stream()
                .map(UserGetResponse::from)
                .toList();
    }

}
