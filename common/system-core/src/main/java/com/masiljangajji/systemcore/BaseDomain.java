package com.masiljangajji.systemcore;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BaseDomain {

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = createdAt;

    protected void setUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

}
