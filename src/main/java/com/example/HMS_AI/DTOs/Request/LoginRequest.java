package com.example.HMS_AI.DTOs.Request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @Schema(example = "test")
    @NotEmpty
    private String userName;

    @Schema(example = "test@1212")
    @NotEmpty
    private String password;

    @Schema(example = "test@gmail.com")
    @Email
    private String email;
}
