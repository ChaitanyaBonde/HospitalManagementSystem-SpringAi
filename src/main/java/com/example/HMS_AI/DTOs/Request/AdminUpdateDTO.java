package com.example.HMS_AI.DTOs.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminUpdateDTO {
    private String email;
    @NotEmpty(message = "Enter Phone Number")
    private String phone;
    private String address;
    @NotEmpty(message = "Enter Full Name")
    private String fullName;
}
