package com.masiljangajji.circuit_breaker.exception;

public class CircuitBreakerNameNotFoundException extends RuntimeException {

    public CircuitBreakerNameNotFoundException() {
        super("Circuit breaker name not found");
    }

}
