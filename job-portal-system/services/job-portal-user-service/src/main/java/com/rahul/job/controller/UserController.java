package com.rahul.job.controller;

import com.rahul.job.mapper.UserMapper;
import com.rahul.job.model.User;
import com.rahul.job.payload.UpdateUserRequest;
import com.rahul.job.response.UserResponse;
import com.rahul.job.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("X-User-Email") String email) throws Exception {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }


    @PutMapping("/update")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("X-User-Email") String email, @RequestBody UpdateUserRequest request) throws Exception {
        UserResponse user = userService.updateUser(email,request);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) throws Exception {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }


    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUser() throws Exception {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(UserMapper.toDtoList(allUser));
    }

    @PatchMapping("/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendUser(@PathVariable Long userId) throws Exception {
        UserResponse user = userService.suspendUser(userId);
        return ResponseEntity.ok(user);
    }


    @PatchMapping("/{userId}/active")
    public ResponseEntity<UserResponse> activeUser(@PathVariable Long userId) throws Exception {
        UserResponse user = userService.activeUser(userId);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId) throws Exception {
        UserResponse user = userService.deleteUser(userId);
        return ResponseEntity.ok(user);
    }



}
