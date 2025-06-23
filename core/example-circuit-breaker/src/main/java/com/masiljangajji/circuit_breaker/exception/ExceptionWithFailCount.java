package com.masiljangajji.circuit_breaker.exception;

public class ExceptionWithFailCount extends RuntimeException {

    public ExceptionWithFailCount(int failCount) {
        super(String.format("실패했습니다 %d", failCount));
    }

}
