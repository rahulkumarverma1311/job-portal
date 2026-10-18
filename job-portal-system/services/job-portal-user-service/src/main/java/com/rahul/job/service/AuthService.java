package com.rahul.job.service;

import com.rahul.job.payload.AuthResponse;
import com.rahul.job.payload.LoginRequest;
import com.rahul.job.payload.SignupRequest;
import org.springframework.stereotype.Service;


public interface AuthService {

    AuthResponse sighUp(SignupRequest request) throws Exception;
    AuthResponse login(LoginRequest request) throws Exception;
}
