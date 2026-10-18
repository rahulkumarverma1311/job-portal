package com.rahul.job.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {

    @Email(message = "email is invalid")
    @NotBlank(message = "email can not blank")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
