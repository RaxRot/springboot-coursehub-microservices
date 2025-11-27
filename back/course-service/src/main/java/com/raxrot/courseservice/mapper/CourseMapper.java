package com.raxrot.courseservice.mapper;

import com.raxrot.courseservice.dto.CourseRequestDTO;
import com.raxrot.courseservice.dto.CourseResponseDTO;
import com.raxrot.courseservice.model.Course;

public class CourseMapper {
    public static Course toCourse(CourseRequestDTO courseRequestDTO){
        Course course=new Course();
        course.setTitle(courseRequestDTO.getTitle());
        course.setDescription(courseRequestDTO.getDescription());
        return course;
    }

    public static CourseResponseDTO toCourseResponseDTO(Course course){
        CourseResponseDTO courseResponseDTO=new CourseResponseDTO();
        courseResponseDTO.setId(course.getId());
        courseResponseDTO.setTitle(course.getTitle());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setAuthorId(course.getAuthorId());
        courseResponseDTO.setImageUrl(course.getImageUrl());
        return courseResponseDTO;
    }
}
