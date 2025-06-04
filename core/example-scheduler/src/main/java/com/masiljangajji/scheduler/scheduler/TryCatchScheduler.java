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
        havingValue = "try-catch"
)
@RequiredArgsConstructor
public class TryCatchScheduler {

    private final UserService userService;

    // 여러개의 쿼리를 보내는 경우 Ex) JPA DeleteAll, Service 로직을 try-catch 로 감싸 커밋 후 처리
    // 실패하는 지점부터 재시도 가능 함
    // 하지만 벌크작업(하나의 쿼리)의 경우에는 똑같이 전체가 재시도 됨
    @Scheduled(
            cron = "${scheduler.jobs.delete-user.cron}",
            zone = "${scheduler.jobs.delete-user.zone}")
    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 5000),
            retryFor = {RuntimeException.class},
            listeners = "loggingRetryListener"
    )
    public void deleteUserCreatedBeforeJobWithTryCatch() {
        log.info("Scheduler - deleteUserByPolicyWithTryCatch 실행 {}", LocalDateTime.now());
        boolean hasError = userService.deleteUserByPolicyWithTryCatch();

        if (hasError) {
            throw new RuntimeException("deleteUserCreatedBeforeJobWithTryCatch failed");
        }
    }

}
