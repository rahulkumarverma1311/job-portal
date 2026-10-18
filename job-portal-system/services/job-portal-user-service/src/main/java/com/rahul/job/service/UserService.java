package com.rahul.job.service;

import com.rahul.job.model.User;
import com.rahul.job.payload.UpdateUserRequest;
import com.rahul.job.response.UserResponse;

import java.util.List;

public interface UserService {


    User getUserByEmail(String email) throws Exception;
    User getUserById (Long userId) throws Exception;
    List<User>getAllUser();
    UserResponse updateUser(String email, UpdateUserRequest request) throws Exception;


    // admin action

    UserResponse suspendUser(Long id) throws Exception;
    UserResponse activeUser(Long id) throws Exception;
    UserResponse deleteUser(Long id) throws Exception;


}
