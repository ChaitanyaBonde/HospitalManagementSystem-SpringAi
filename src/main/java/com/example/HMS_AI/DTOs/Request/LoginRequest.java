package com.example.HMS_AI.DTOs.Request;

import com.example.HMS_AI.Enum.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
