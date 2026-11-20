package com.storeapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "can not be blank")
    @Email
    private String email;
    @NotBlank(message = "can not br blank")
    private String password;
}
