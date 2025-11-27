package com.raxrot.userservice.mapper;

import com.raxrot.userservice.dto.UserRequestDTO;
import com.raxrot.userservice.dto.UserResponseDTO;
import com.raxrot.userservice.model.User;

public class UserMapper {
    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole("USER");
        return user;
    }

    public static UserResponseDTO toUserResponseDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt());
        return userResponseDTO;
    }
}
