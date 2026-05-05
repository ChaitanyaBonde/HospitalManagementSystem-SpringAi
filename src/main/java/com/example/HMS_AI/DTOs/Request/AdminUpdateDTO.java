package com.example.HMS_AI.DTOs.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminUpdateDTO {

    @Schema(example = "test@gmail.com")
    @Email(message = "Enter valid Email")
    private String email;

    @Schema(example = "1111111111")
    @NotEmpty(message = "Enter Phone Number")
    private String phone;

    @Schema(example = "Nagpur, Maharashtra")
    private String address;

    @Schema(example = "TestUser")
    @NotEmpty(message = "Enter Full Name")
    private String fullName;
}
