package com.masiljangajji.circuit_breaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CircuitBreakerApplication {

    /**
     * Spring Boot 애플리케이션을 시작하는 메인 진입점입니다.
     *
     * @param args 커맨드라인 인수
     */
    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApplication.class, args);
    }

}
