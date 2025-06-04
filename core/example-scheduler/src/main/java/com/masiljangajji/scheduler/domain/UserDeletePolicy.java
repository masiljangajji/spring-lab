package com.masiljangajji.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Getter
@Configuration
@AllArgsConstructor
public class UserDeletePolicy {

    @Bean
    @Profile("local")
    public LocalDateTime cutoff() {
        return LocalDateTime.now().minusSeconds(5);
    }


}
