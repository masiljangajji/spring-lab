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

    /**
     * `/api/call` 엔드포인트에서 FaultyService의 `call()` 메서드를 호출하여 결과를 반환합니다.
     *
     * @return FaultyService의 `call()` 메서드 실행 결과 문자열
     */
    @GetMapping("/call")
    public String callOnce() {
        return faultyService.call();
    }

    /**
     * 지정된 회로 차단기의 현재 상태를 문자열로 반환합니다.
     *
     * @param circuitBreakerName 상태를 조회할 회로 차단기의 이름
     * @return "현재 상태: "와 회로 차단기 상태가 결합된 문자열
     */
    @GetMapping("/status")
    public String getCircuitBreakerStatus(@RequestParam String circuitBreakerName) {
        return "현재 상태: " + circuitBreakerStatusService.getStatus(circuitBreakerName);
    }

}
