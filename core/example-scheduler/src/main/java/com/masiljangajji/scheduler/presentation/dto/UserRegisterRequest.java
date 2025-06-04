package com.masiljangajji.scheduler.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(@NotBlank String name) {
}
