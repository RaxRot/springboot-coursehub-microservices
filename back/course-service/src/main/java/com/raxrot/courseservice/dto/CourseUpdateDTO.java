package com.raxrot.courseservice.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseUpdateDTO {
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
}
