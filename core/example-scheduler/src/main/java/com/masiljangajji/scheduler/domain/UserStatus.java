package com.masiljangajji.scheduler.domain;

import lombok.AllArgsConstructor;
import java.util.Arrays;

@AllArgsConstructor
public enum UserStatus {

    ACTIVE("활성"),
    INACTIVE("비활성");

    private final String status;

    public static UserStatus from(String status) {
        return Arrays.stream(UserStatus.values())
                .filter(s -> s.name().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
