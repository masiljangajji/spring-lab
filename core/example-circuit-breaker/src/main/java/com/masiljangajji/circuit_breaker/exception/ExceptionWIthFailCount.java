package com.masiljangajji.circuit_breaker.exception;

public class ExceptionWIthFailCount extends RuntimeException {

    public ExceptionWIthFailCount(int failCount) {
        super(String.format("실패했습니다 %d", failCount));
    }

}
