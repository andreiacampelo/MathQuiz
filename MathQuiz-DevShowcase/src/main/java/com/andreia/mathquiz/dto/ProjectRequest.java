package com.andreia.mathquiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record ProjectRequest(
    @NotBlank String title,
    @NotBlank String question,
    @NotBlank String correctAnswer,
    @NotNull Long profileId,
    Set<Long> technologyIds
) {}
