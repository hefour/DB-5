package com.collaball.domain.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectCreateRequest(
        @NotBlank(message = "Project name is required.")
        @Size(max = 100, message = "Project name must be 100 characters or less.")
        String name,

        @Size(max = 1000, message = "Project description must be 1000 characters or less.")
        String description
) {
}
