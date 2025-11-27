package com.raxrot.userservice.service.impl;

import com.raxrot.userservice.dto.UserRequestDTO;
import com.raxrot.userservice.dto.UserResponseDTO;
import com.raxrot.userservice.dto.UserUpdateDTO;
import com.raxrot.userservice.exception.ApiException;
import com.raxrot.userservice.mapper.UserMapper;
import com.raxrot.userservice.model.User;
import com.raxrot.userservice.repository.UserRepository;
import com.raxrot.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())
                || userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new ApiException("Username or email already exists", HttpStatus.CONFLICT);
        }

        User user = UserMapper.toUser(userRequestDTO);
        User savedUser=userRepository.save(user);
        return UserMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User>users=userRepository.findAll();
        List<UserResponseDTO>userResponseDTOS=users.stream()
                .map(user->UserMapper.toUserResponseDTO(user))
                .collect(Collectors.toList());
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new ApiException("User not found",HttpStatus.NOT_FOUND));
        return UserMapper.toUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        // Username
        if (userUpdateDTO.getUsername() != null && !userUpdateDTO.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(userUpdateDTO.getUsername())) {
                throw new ApiException("Username already exists", HttpStatus.CONFLICT);
            }
            user.setUsername(userUpdateDTO.getUsername());
        }

        // Email
        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(userUpdateDTO.getEmail())) {
                throw new ApiException("Email already exists", HttpStatus.CONFLICT);
            }
            user.setEmail(userUpdateDTO.getEmail());
        }

        User saved = userRepository.save(user);
        return UserMapper.toUserResponseDTO(saved);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new ApiException("User not found",HttpStatus.NOT_FOUND);
        }else {
            userRepository.deleteById(id);
        }
    }
}
