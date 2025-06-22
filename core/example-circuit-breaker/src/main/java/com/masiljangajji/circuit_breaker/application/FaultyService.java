package com.masiljangajji.circuit_breaker.application;

import com.masiljangajji.circuit_breaker.exception.ExceptionWIthFailCount;
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

    /**
     * 50% 확률로 실패하거나 성공 메시지를 반환하는 메서드입니다.
     *
     * 실패 시 실패 횟수를 증가시키고 예외를 발생시키며, 성공 시 성공 횟수를 증가시키고 성공 메시지를 반환합니다.
     * Resilience4j의 Circuit Breaker가 적용되어 있으며, 실패 시 지정된 fallback 메서드가 호출됩니다.
     *
     * @return 성공 시 현재 성공 횟수를 포함한 메시지
     * @throws ExceptionWIthFailCount 실패 시 현재 실패 횟수를 포함한 예외
     */
    @CircuitBreaker(name = "local", fallbackMethod = "fallBack")
    public String call() {
        if (ThreadLocalRandom.current().nextInt(10) < 5) { // 성공확률 50%
            resultTracker.incrementFailCount();
            throw new ExceptionWIthFailCount(resultTracker.getFailCount());
        }
        resultTracker.incrementSuccessCount();
        return String.format("성공했습니다 %d", resultTracker.getSuccessCount());
    }

    /**
     * Circuit breaker의 fallback 메서드로, 예외 발생 시 호출되어 에러 메시지, 총 시도 횟수, 실패율 정보를 포함한 메시지를 반환합니다.
     *
     * @param t 발생한 예외 객체
     * @return 에러 메시지, 시도 횟수, 실패율이 포함된 포맷된 문자열
     */
    public String fallBack(Throwable t) {

        return String.format("fallback 호출, error = %s, 총 시도 횟수 = %d, 실패율 = %.2f%%",
                t.getMessage(), resultTracker.getTotalAttempts(), resultTracker.getFailureRate());
    }

}
