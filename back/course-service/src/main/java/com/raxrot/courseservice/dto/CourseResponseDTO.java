package com.raxrot.courseservice.dto;

import lombok.Data;

@Data
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long authorId;
}
