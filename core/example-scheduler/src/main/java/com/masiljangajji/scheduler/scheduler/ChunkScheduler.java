package com.masiljangajji.scheduler.scheduler;

import com.masiljangajji.scheduler.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@ConditionalOnProperty(
        prefix = "scheduler.jobs.delete-user",
        name = "strategy",
        havingValue = "chunk"
)
@RequiredArgsConstructor
public class ChunkScheduler {

    private final UserService userService;

    @Value("${scheduler.jobs.delete-user.chunk-size}")
    private Integer chunkSize;

    // 벌크작업(하나의 쿼리)에 대해 청크를 나눔으로써 작업을 분산
    // 여러개의 쿼리를 날리지만 작업이 청크 단위로 묶임으로서 보다 효율적
    // 실패하는 지점부터 재시도
    // 삭제가 필요한 ids 를 메모리에 저장해야 되기 때문에 메모리에 부담이 생김
    // 따라서 페이징해서 가져오거나 커서를 사용해야 함 -> 메모리 최적화 하면서 사용 가능
    @Scheduled(
            cron = "${scheduler.jobs.delete-user.cron}",
            zone = "${scheduler.jobs.delete-user.zone}")
    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 5000),
            retryFor = {RuntimeException.class},
            listeners = "loggingRetryListener"
    )
    public void deleteUserCreatedBeforeJobWithChunks() {
        log.info("Scheduler - deleteUserCreatedBeforeJobWithChunks 실행 {}", LocalDateTime.now());
        List<UUID> ids = userService.findAllIdsByPolicy();

        for (int i = 0; i < ids.size(); i += chunkSize) {
            List<UUID> chunk = ids.subList(i, Math.min(ids.size(), i + chunkSize));
            userService.deleteUserByPolicyWithIds(chunk);
        }

    }

}
