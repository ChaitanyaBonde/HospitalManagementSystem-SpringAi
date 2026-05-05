package com.example.HMS_AI.DTOs.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @Schema(example = "test")
    @NotEmpty(message = "Enter valid UserName")
    private String userName;

    @Schema(example = "test@gmail.com")
    @NotEmpty(message = "Enter valid Email")
    @Email(message = "Enter valid Email")
    private String email;

    @Schema(example = "test@1212")
    @NotEmpty(message = "Password Cannot Be null")
    private String password;
}
