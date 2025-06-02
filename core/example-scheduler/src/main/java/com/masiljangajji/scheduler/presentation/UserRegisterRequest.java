package com.masiljangajji.scheduler.presentation;

import lombok.NonNull;

public record UserRegisterRequest(@NonNull String name, @NonNull String status) {
}
