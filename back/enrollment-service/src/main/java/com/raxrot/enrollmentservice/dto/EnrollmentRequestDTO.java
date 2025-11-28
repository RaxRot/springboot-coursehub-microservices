package com.raxrot.enrollmentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
