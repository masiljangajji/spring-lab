package com.masiljangajji.scheduler.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component("loggingRetryListener")
@Slf4j
public class LoggingRetryListener implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        log.info("[Retry] 시작 – 최대 시도 횟수: {}", context.getRetryCount() + 1);
        return true;
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context,
                                                 RetryCallback<T, E> callback,
                                                 Throwable throwable) {
        int attempt = context.getRetryCount();
        log.warn("[Retry] #{} 실패 – 예외: {}", attempt, throwable.toString());
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context,
                                               RetryCallback<T, E> callback,
                                               Throwable throwable) {
        if (throwable == null) {
            log.info("[Retry] 성공 – 총 시도 횟수: {}", context.getRetryCount());
        } else {
            log.error("[Retry] 최종 실패 – 총 시도 횟수: {}, 마지막 예외: {}",
                    context.getRetryCount(), throwable.toString());
        }
    }

}
