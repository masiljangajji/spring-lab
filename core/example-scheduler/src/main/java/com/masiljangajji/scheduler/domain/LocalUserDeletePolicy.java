package com.masiljangajji.scheduler.domain;

import lombok.Getter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Component
@Profile("local")
public class LocalUserDeletePolicy implements UserDeletePolicy {

    public LocalDateTime getCutoff() {
        return LocalDateTime.now().minusSeconds(5);
    }

}
