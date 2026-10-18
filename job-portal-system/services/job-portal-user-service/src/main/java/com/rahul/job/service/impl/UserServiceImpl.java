package com.rahul.job.service.impl;

import com.rahul.job.domain.UserStatus;
import com.rahul.job.mapper.UserMapper;
import com.rahul.job.model.User;
import com.rahul.job.payload.UpdateUserRequest;
import com.rahul.job.repository.UserRepository;
import com.rahul.job.response.UserResponse;
import com.rahul.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("user naot found with email: "+ email);
        }else{
        return user;
        }
    }

    @Override
    public User getUserById(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found with id : " + userId));
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateUser(String email, UpdateUserRequest request) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("user naot found with email: " + email);
        }

        if(request.getFullName() != null){
            user.setFullName(request.getFullName());
        }
        if(request.getPhone() != null)
            user.setPhone(request.getPhone());
        if(request.getProfileImage() != null)
            user.setProfileImage(request.getProfileImage());

        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {

        User user = getUserById(id);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponse activeUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        return UserMapper.toDto(userRepository.save(user));
    }
}
