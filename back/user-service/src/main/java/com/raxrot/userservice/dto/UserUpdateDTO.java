package com.raxrot.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @Size(min = 3, max = 30, message = "Username must be 3–30 characters long")
    private String username;

    @Email(message = "Email must be valid")
    private String email;
}
