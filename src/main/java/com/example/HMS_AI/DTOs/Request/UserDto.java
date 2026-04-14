package com.example.HMS_AI.DTOs.Request;

import com.example.HMS_AI.Enum.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "Enter valid UserName")
    private String userName;
    @NotEmpty(message = "Enter valid Email")
    @Email(message = "Enter valid Email")
    private String email;
    @NotEmpty(message = "Password Cannot Be null")
    private String password;
}
