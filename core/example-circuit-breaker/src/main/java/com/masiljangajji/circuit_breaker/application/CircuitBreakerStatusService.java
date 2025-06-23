package com.masiljangajji.circuit_breaker.application;

import com.masiljangajji.circuit_breaker.exception.CircuitBreakerNameNotFoundException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CircuitBreakerStatusService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public String getStatus(String circuitBreakerName) {

        if (circuitBreakerRegistry.getAllCircuitBreakers().stream()
                .map(CircuitBreaker::getName)
                .noneMatch(name -> name.equals(circuitBreakerName))) {
            throw new CircuitBreakerNameNotFoundException();
        }

        return circuitBreakerRegistry.circuitBreaker(circuitBreakerName).getState().name();
    }

}
