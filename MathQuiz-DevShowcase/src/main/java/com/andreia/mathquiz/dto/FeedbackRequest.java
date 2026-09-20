package com.andreia.mathquiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FeedbackRequest(
    @NotNull Long projectId,
    @NotBlank String studentAnswer
) {}
