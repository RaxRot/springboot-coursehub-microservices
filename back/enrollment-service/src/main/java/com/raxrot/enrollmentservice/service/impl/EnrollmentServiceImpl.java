package com.raxrot.enrollmentservice.service.impl;

import com.raxrot.enrollmentservice.dto.EnrollmentRequestDTO;
import com.raxrot.enrollmentservice.dto.EnrollmentResponseDTO;
import com.raxrot.enrollmentservice.exception.ApiException;
import com.raxrot.enrollmentservice.mapper.EnrollmentMapper;
import com.raxrot.enrollmentservice.model.Enrollment;
import com.raxrot.enrollmentservice.repository.EnrollmentRepository;
import com.raxrot.enrollmentservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO dto) {
        //feign!!!! top check course and user

        if (enrollmentRepository.existsByUserIdAndCourseId(dto.getUserId(), dto.getCourseId())) {
            throw new ApiException("User is already enrolled in this course", HttpStatus.CONFLICT);
        }

        Enrollment enrollment= EnrollmentMapper.toEnrollment(dto);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.toEnrollmentResponseDTO(savedEnrollment);
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByUser(Long userId) {
        return enrollmentRepository.findByUserId(userId)
                .stream()
                .map(EnrollmentMapper::toEnrollmentResponseDTO)
                .toList();
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(EnrollmentMapper::toEnrollmentResponseDTO)
                .toList();
    }
}
