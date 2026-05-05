package com.example.HMS_AI.DTOs.Request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PatientDTO {
    @Schema(example = "1")
    private Integer patientId;

    @Schema(example = "testpatient@gmail.com")
    @Email(message = "Enter Vaild emial")
    private String email;

    @Schema(example = "test patient")
    @NotEmpty(message = "Enter Your Name")
    private String fullName;

    @Schema(example = "28")
    private Integer age;

    @Schema(example = "B positive")
    private String bloodGroup;

    @Schema(example = "1111111111")
    private String phoneNumber;
}
