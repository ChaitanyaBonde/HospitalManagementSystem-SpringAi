package com.example.HMS_AI.DTOs.Request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PatientDTO {
    private Integer patientId;
    private String email;
    @NotEmpty(message = "Enter Your Name")
    private String fullName;
    private Integer age;
    private String bloodGroup;
    private String phoneNumber;
}
