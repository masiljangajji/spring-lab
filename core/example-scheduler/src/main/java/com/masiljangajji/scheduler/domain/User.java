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


    public static User of(String name) {
        UUID id = UUID.randomUUID();
        return new User(id, name);
    }

}
