package com.masiljangajji.scheduler.scheduler;

import com.masiljangajji.scheduler.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final UserService userService;

    private final SchedulerProperties schedulerProperties;

    @Scheduled(cron = "*/15 * * * * *", zone = "Asia/Seoul")
    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 5000),
            retryFor = {RuntimeException.class},
            listeners = "loggingRetryListener"
    )
    public void deleteUserCreatedBeforeJob() {
        log.info("deleteUserCreatedBeforeJob 실행 {}", LocalDateTime.now());
        userService.deleteUserByPolicy();
    }

}
