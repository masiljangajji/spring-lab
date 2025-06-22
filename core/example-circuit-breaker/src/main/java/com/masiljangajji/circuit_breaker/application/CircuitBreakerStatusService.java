package com.masiljangajji.circuit_breaker.application;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CircuitBreakerStatusService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public String getStatus(String circuitBreakerName) {
        return circuitBreakerRegistry.circuitBreaker(circuitBreakerName).getState().name();
    }

}
