package com.collaball.domain.project.dto;

import com.collaball.domain.project.entity.Project;

import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        String status,
        String statusLabel,
        Long ownerId,
        String ownerName,
        boolean editable,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProjectResponse from(Project project, Long currentUserId) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus().name(),
                project.getStatus().getLabel(),
                project.getOwner().getId(),
                project.getOwner().getName(),
                project.isOwnedBy(currentUserId),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
