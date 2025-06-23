package com.masiljangajji.circuit_breaker.application;

import com.masiljangajji.circuit_breaker.exception.ExceptionWithFailCount;
import com.masiljangajji.circuit_breaker.domain.ResultTracker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaultyService {


    private final ResultTracker resultTracker;

    @CircuitBreaker(name = "local", fallbackMethod = "fallBack")
    public String call() {
        if (ThreadLocalRandom.current().nextInt(10) < 5) { // 성공확률 50%
            resultTracker.incrementFailCount();
            throw new ExceptionWithFailCount(resultTracker.getFailCount());
        }
        resultTracker.incrementSuccessCount();
        return String.format("성공했습니다 %d", resultTracker.getSuccessCount());
    }

    public String fallBack(Throwable t) {

        return String.format("fallback 호출, error = %s, 총 시도 횟수 = %d, 실패율 = %.2f%%",
                t.getMessage(), resultTracker.getTotalAttempts(), resultTracker.getFailureRate());
    }

}
