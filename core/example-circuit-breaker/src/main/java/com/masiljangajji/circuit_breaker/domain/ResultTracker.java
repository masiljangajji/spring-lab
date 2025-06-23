package com.masiljangajji.circuit_breaker.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ResultTracker {

    private int failCount = 0;

    private int successCount = 0;

    public void incrementFailCount() {
        failCount++;
    }

    public void incrementSuccessCount() {
        successCount++;
    }

    public int getTotalAttempts() {
        return failCount + successCount;
    }

    public double getFailureRate() {
        int total = getTotalAttempts();

        if (total == 0) {
            return 0.0;
        }

        return (double) failCount / total * 100;
    }

}
