package com.masiljangajji.circuit_breaker.presentation;

import com.masiljangajji.circuit_breaker.application.CircuitBreakerStatusService;
import com.masiljangajji.circuit_breaker.application.FaultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return "현재 상태: " + circuitBreakerStatusService.getStatus(circuitBreakerName);
    }

}
