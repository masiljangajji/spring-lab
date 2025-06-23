package com.masiljangajji.circuit_breaker.exception;

public class CircuitBreakerNameValidationException extends RuntimeException {

    public CircuitBreakerNameValidationException() {
        super("circuitBreakerName cannot be null or empty");
    }

}
