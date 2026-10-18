package com.rahul.job.mapper;

import com.rahul.job.model.User;
import com.rahul.job.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse toDto(User user){
        UserResponse dto  = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setProfileImage(user.getProfileImage());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setLastLoginTime(user.getLastLoginTime());
        dto.setCreateAt(user.getCreateAt());
        return dto;
    }


    public static List<UserResponse> toDtoList(List<User> userList){

        return userList.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }




}
