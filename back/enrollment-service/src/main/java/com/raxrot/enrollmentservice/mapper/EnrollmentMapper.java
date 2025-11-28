package com.raxrot.enrollmentservice.mapper;

import com.raxrot.enrollmentservice.dto.EnrollmentRequestDTO;
import com.raxrot.enrollmentservice.dto.EnrollmentResponseDTO;
import com.raxrot.enrollmentservice.model.Enrollment;

public class EnrollmentMapper {

    public static Enrollment toEnrollment(EnrollmentRequestDTO enrollmentRequestDTO){
        Enrollment enrollment=new Enrollment();
        enrollment.setUserId(enrollmentRequestDTO.getUserId());
        enrollment.setCourseId(enrollmentRequestDTO.getCourseId());
        return enrollment;
    }

    public static EnrollmentResponseDTO toEnrollmentResponseDTO(Enrollment enrollment){
        EnrollmentResponseDTO enrollmentResponseDTO=new EnrollmentResponseDTO();
        enrollmentResponseDTO.setId(enrollment.getId());
        enrollmentResponseDTO.setUserId(enrollment.getUserId());
        enrollmentResponseDTO.setCourseId(enrollment.getCourseId());
        enrollmentResponseDTO.setEnrolledAt(enrollment.getEnrolledAt());
        return enrollmentResponseDTO;
    }
}
