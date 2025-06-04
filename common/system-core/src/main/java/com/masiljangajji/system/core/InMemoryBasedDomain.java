package com.masiljangajji.system.core;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class InMemoryBasedDomain {

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = createdAt;

    protected void setUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

}
