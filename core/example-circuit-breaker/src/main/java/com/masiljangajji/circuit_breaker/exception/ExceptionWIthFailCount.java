package com.masiljangajji.circuit_breaker.exception;

public class ExceptionWIthFailCount extends RuntimeException {

    /**
     * 실패 횟수를 포함한 메시지로 예외를 생성합니다.
     *
     * @param failCount 실패한 횟수
     */
    public ExceptionWIthFailCount(int failCount) {
        super(String.format("실패했습니다 %d", failCount));
    }

}
