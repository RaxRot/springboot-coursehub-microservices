package com.raxrot.courseservice.controller;


import com.raxrot.courseservice.client.UserDTO;
import com.raxrot.courseservice.dto.CourseRequestDTO;
import com.raxrot.courseservice.dto.CourseResponseDTO;
import com.raxrot.courseservice.dto.CourseUpdateDTO;
import com.raxrot.courseservice.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO>createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);

        CourseResponseDTO courseResponseDTO=courseService.createCourse(courseRequestDTO,userDTO);
        return new ResponseEntity<>(courseResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>>getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        CourseResponseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByAuthorId(@PathVariable Long id) {
        List<CourseResponseDTO> courses = courseService.getCoursesByAuthorId(id);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseResponseDTO>> searchCourses(@RequestParam String query) {
        List<CourseResponseDTO> courses = courseService.searchCourses(query);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateDTO courseUpdateDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        CourseResponseDTO course = courseService.updateCourse(id, courseUpdateDTO, userDTO);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        courseService.deleteCourse(id, userDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<CourseResponseDTO> uploadCourseImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        CourseResponseDTO updated = courseService.uploadCourseImage(id, image, userDTO);
        return ResponseEntity.ok(updated);
    }

}
