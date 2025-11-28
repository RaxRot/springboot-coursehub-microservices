package com.raxrot.enrollmentservice.controller;

import com.raxrot.enrollmentservice.dto.EnrollmentRequestDTO;
import com.raxrot.enrollmentservice.dto.EnrollmentResponseDTO;
import com.raxrot.enrollmentservice.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(@Valid @RequestBody EnrollmentRequestDTO dto) {
        EnrollmentResponseDTO response = enrollmentService.enroll(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByUser(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }
}
