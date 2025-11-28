package com.raxrot.enrollmentservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollmentResponseDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private LocalDateTime enrolledAt;
}
