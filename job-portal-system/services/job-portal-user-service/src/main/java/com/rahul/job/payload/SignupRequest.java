package com.rahul.job.payload;

import com.rahul.job.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "name can not blank")
    private String fullName;
    @Email(message = "email is invalid")
    @NotBlank(message = "email can not blank")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
    private String phone;
    @NotNull(message = "role can not blank")
    private UserRole role;
}
