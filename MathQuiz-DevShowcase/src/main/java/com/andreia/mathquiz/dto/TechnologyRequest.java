package com.andreia.mathquiz.dto;

import jakarta.validation.constraints.NotBlank;

public record TechnologyRequest(@NotBlank String name) {}
