package com.masiljangajji.scheduler.scheduler;

import com.masiljangajji.scheduler.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@ConditionalOnProperty(
        prefix = "scheduler.jobs.delete-user",
        name = "strategy",
        havingValue = "simple"
)
@RequiredArgsConstructor
public class Scheduler {

    private final UserService userService;

    // 분산 환경일시 ShedLock 등을 통해 중복 실행 관리 가능
    // 실패시 deleteUserCreatedBeforeJob 을 다시 실행, @Transectional 로 인해 전체 롤백
    // 벌크 작업의 경우에 항상 전체를 재시도 하기 때문에 비효율적임
    @Scheduled(
            cron = "${scheduler.jobs.delete-user.cron}",
            zone = "${scheduler.jobs.delete-user.zone}")
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
