package com.storeapi.dtos;

import com.storeapi.validation.LowerCase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterUserRequest {

    @NotBlank(message = "name is required")
    @Size(max = 255, message = "must be max 255 character")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @LowerCase(message = "email must be lower case")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 25, message = "must be at least 6 characters and max 25 char")
    private String password;

}
