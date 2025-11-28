package com.raxrot.enrollmentservice.service;

import com.raxrot.enrollmentservice.dto.EnrollmentRequestDTO;
import com.raxrot.enrollmentservice.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDTO enroll(EnrollmentRequestDTO dto);
    List<EnrollmentResponseDTO> getEnrollmentsByUser(Long userId);
    List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId);
}
