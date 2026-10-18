package com.rahul.job.service.impl;

import com.rahul.job.domain.UserRole;
import com.rahul.job.domain.UserStatus;
import com.rahul.job.mapper.UserMapper;
import com.rahul.job.model.User;
import com.rahul.job.payload.AuthResponse;
import com.rahul.job.payload.LoginRequest;
import com.rahul.job.payload.SignupRequest;
import com.rahul.job.repository.UserRepository;
import com.rahul.job.security.CustomUserDetailsService;
import com.rahul.job.security.JwtProvider;
import com.rahul.job.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse sighUp(SignupRequest request) throws Exception {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("user already exist with this email");
        }
        if(request.getRole() == UserRole.ROLE_ADMIN)
            throw new Exception("you cant sign up with role admin by yourself");


        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole())
                .lastLoginTime(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User saved = userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication,saved.getId());
        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome "+ saved.getFullName());
        response.setMessage("Register successfully");
        response.setJwt(jwt );
        response.setUser(UserMapper.toDto(saved));
        return response;
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {
        Authentication authentication = authenticate(request.getEmail(),request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(request.getEmail());
        String jwt = jwtProvider.generateToken(authentication,user.getId());
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);
        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setUser(UserMapper.toDto(user));
        response.setTitle("welcome back "+ user.getFullName());
        response.setMessage("login successfully");

        return response;
    }

    private Authentication authenticate( String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails== null){
            throw new Exception("user not found with email : "+ email);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new Exception("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
