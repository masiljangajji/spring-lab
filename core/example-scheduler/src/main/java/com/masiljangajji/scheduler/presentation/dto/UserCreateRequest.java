package com.masiljangajji.scheduler.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(@NotBlank String name) {
}
