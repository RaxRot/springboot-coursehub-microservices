package com.raxrot.enrollmentservice.repository;

import com.raxrot.enrollmentservice.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);

    List<Enrollment> findByCourseId(Long courseId);

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
