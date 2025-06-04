package com.masiljangajji.scheduler.domain;

import com.masiljangajji.system.core.InMemoryBasedDomain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends InMemoryBasedDomain {

    private final UUID id;

    private String name;

    private UserStatus status;

    public String getStatus() {
        return status.name();
    }

    public static User of(String name, UserStatus status) {
        UUID id = UUID.randomUUID();
        return new User(id, name, status);
    }

    public void changeStatus(UserStatus status) {
        this.status = status;
        this.setUpdatedAt();
    }

}
