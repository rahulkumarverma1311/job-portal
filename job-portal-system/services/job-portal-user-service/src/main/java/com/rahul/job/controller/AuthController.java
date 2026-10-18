package com.rahul.job.controller;

import com.rahul.job.payload.AuthResponse;
import com.rahul.job.payload.LoginRequest;
import com.rahul.job.payload.SignupRequest;
import com.rahul.job.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody @Valid SignupRequest request) throws Exception {
        return authService.sighUp(request);
    }


    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) throws Exception {
        return authService.login(request);
    }
}
