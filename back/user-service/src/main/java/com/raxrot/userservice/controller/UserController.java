package com.raxrot.userservice.controller;

import com.raxrot.userservice.dto.UserRequestDTO;
import com.raxrot.userservice.dto.UserResponseDTO;
import com.raxrot.userservice.dto.UserUpdateDTO;
import com.raxrot.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO>createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO=userService.createUser(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>>getAllUsers(){
        List<UserResponseDTO>userResponseDTOS=userService.getAllUsers();
        return ResponseEntity.ok(userResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO>getUserById(@PathVariable Long id){
        UserResponseDTO userResponseDTO=userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO>updateUser(@PathVariable Long id,@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        UserResponseDTO userResponseDTO=userService.updateUser(id,userUpdateDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
