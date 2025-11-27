package com.raxrot.courseservice.service;

import com.raxrot.courseservice.client.UserDTO;
import com.raxrot.courseservice.dto.CourseRequestDTO;
import com.raxrot.courseservice.dto.CourseResponseDTO;
import com.raxrot.courseservice.dto.CourseUpdateDTO;

import java.util.List;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO, UserDTO userDTO);
    List<CourseResponseDTO> getAllCourses();
    CourseResponseDTO getCourseById(Long id);
    List<CourseResponseDTO> getCoursesByAuthorId(Long authorId);
    List<CourseResponseDTO> searchCourses(String query);
    CourseResponseDTO updateCourse(Long id, CourseUpdateDTO courseUpdateDTO, UserDTO userDTO);
    void deleteCourse(Long id, UserDTO userDTO);
}
