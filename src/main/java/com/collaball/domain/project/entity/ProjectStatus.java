package com.collaball.domain.project.entity;

public enum ProjectStatus {

    IN_PROGRESS("진행 중"),
    EVALUATION_PENDING("평가 대기"),
    EVALUATION_COMPLETED("평가 완료");

    private final String label;

    ProjectStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
