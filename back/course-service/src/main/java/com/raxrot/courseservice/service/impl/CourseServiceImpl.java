package com.raxrot.courseservice.service.impl;

import com.raxrot.courseservice.client.UserDTO;
import com.raxrot.courseservice.dto.CourseRequestDTO;
import com.raxrot.courseservice.dto.CourseResponseDTO;
import com.raxrot.courseservice.dto.CourseUpdateDTO;
import com.raxrot.courseservice.exception.ApiException;
import com.raxrot.courseservice.mapper.CourseMapper;
import com.raxrot.courseservice.model.Course;
import com.raxrot.courseservice.repository.CourseRepository;
import com.raxrot.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO, UserDTO userDTO) {
        Course course= CourseMapper.toCourse(courseRequestDTO);
        course.setAuthorId(userDTO.getId());

        Course savedCourse=courseRepository.save(course);
        return CourseMapper.toCourseResponseDTO(savedCourse);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course>courses= courseRepository.findAll();
        return courses.stream().map(CourseMapper::toCourseResponseDTO).toList();
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course=courseRepository.findById(id)
                .orElseThrow(()->new ApiException("Course not found", HttpStatus.NOT_FOUND));
        return CourseMapper.toCourseResponseDTO(course);
    }

    @Override
    public List<CourseResponseDTO> getCoursesByAuthorId(Long authorId) {
        List<Course>courses=courseRepository.findByAuthorId(authorId);
        return courses.stream().map(CourseMapper::toCourseResponseDTO).toList();
    }

    @Override
    public List<CourseResponseDTO> searchCourses(String query) {
        List<Course>courses=courseRepository.findByTitleContainingIgnoreCase(query);
        return courses.stream().map(CourseMapper::toCourseResponseDTO).toList();
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseUpdateDTO courseUpdateDTO, UserDTO userDTO) {
        Course course=courseRepository.findById(id)
                .orElseThrow(()->new ApiException("Course not found", HttpStatus.NOT_FOUND));

        if (!course.getAuthorId().equals(userDTO.getId())){
            throw new ApiException("You are not authorized to update this course", HttpStatus.UNAUTHORIZED);
        }

        if (courseUpdateDTO.getTitle() != null) {
            course.setTitle(courseUpdateDTO.getTitle());
        }

        if (courseUpdateDTO.getDescription() != null) {
            course.setDescription(courseUpdateDTO.getDescription());
        }

        Course updatedCourse=courseRepository.save(course);

        return CourseMapper.toCourseResponseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id, UserDTO userDTO) {
        Course course=courseRepository.findById(id)
                .orElseThrow(()->new ApiException("Course not found", HttpStatus.NOT_FOUND));

        if (!course.getAuthorId().equals(userDTO.getId())){
            throw new ApiException("You are not authorized to delete this course", HttpStatus.UNAUTHORIZED);
        }

        courseRepository.delete(course);
    }
}
