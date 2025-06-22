package com.masiljangajji.circuit_breaker.application;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CircuitBreakerStatusService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    /****
     * 지정한 회로 차단기의 현재 상태를 문자열로 반환합니다.
     *
     * @param circuitBreakerName 상태를 조회할 회로 차단기의 이름
     * @return 회로 차단기의 현재 상태 이름
     */
    public String getStatus(String circuitBreakerName) {
        return circuitBreakerRegistry.circuitBreaker(circuitBreakerName).getState().name();
    }

}
