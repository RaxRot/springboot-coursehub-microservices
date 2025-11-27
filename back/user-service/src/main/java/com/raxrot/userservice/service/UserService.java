package com.raxrot.userservice.service;

import com.raxrot.userservice.dto.UserRequestDTO;
import com.raxrot.userservice.dto.UserResponseDTO;
import com.raxrot.userservice.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    List<UserResponseDTO>getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Long id);
}
