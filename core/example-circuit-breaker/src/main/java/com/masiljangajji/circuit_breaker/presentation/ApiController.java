package com.masiljangajji.circuit_breaker.presentation;

import com.masiljangajji.circuit_breaker.application.CircuitBreakerStatusService;
import com.masiljangajji.circuit_breaker.application.FaultyService;
import com.masiljangajji.circuit_breaker.exception.CircuitBreakerNameValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final FaultyService faultyService;

    private final CircuitBreakerStatusService circuitBreakerStatusService;

    @GetMapping("/call")
    public String callOnce() {
        return faultyService.call();
    }

    @GetMapping("/status")
    public String getCircuitBreakerStatus(@RequestParam String circuitBreakerName) {

        if (Objects.isNull(circuitBreakerStatusService) || circuitBreakerName.isBlank()) {
            throw new CircuitBreakerNameValidationException();
        }

        return "현재 상태: " + circuitBreakerStatusService.getStatus(circuitBreakerName);
    }

}
