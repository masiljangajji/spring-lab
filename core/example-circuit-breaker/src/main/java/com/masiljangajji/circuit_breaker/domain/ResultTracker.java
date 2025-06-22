package com.masiljangajji.circuit_breaker.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ResultTracker {

    private int failCount = 0;

    private int successCount = 0;

    /**
     * 실패 횟수를 1 증가시킵니다.
     */
    public void incrementFailCount() {
        failCount++;
    }

    /**
     * 성공 횟수를 1 증가시킵니다.
     */
    public void incrementSuccessCount() {
        successCount++;
    }

    /**
     * 실패 및 성공 시도의 총 횟수를 반환합니다.
     *
     * @return 실패 횟수와 성공 횟수의 합계
     */
    public int getTotalAttempts() {
        return failCount + successCount;
    }

    /**
     * 전체 시도 횟수 중 실패 비율을 백분율로 반환합니다.
     *
     * @return 실패 비율(%) 값
     */
    public double getFailureRate() {
        int total = getTotalAttempts();
        return (double) failCount / total * 100;
    }

}
