package com.raxrot.courseservice.repository;

import com.raxrot.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthorId(Long ownerId);
    List<Course> findByTitleContainingIgnoreCase(String title);
}
