package com.example.HMS_AI.DTOs.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
public class DoctorUpdateDTO {
    @NotEmpty(message = "Enter Name")
    private String fullName;
    private String specialization;
    private String experience;
    private String availability;
    private Integer available;
    @NotEmpty(message = "Enter Phone Number")
    private String phoneNumber;
    private String email;
}
