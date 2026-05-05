package com.example.HMS_AI.DTOs.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
public class DoctorUpdateDTO {

    @Schema(example = "1")
    private Integer doctorId;

    @Schema(example = "testDoctor")
    @NotEmpty(message = "Enter Name")
    private String fullName;

    @Schema(example = "PHD")
    private String specialization;

    @Schema(example = "3 years")
    private String experience;

    @Schema(example = "9 AM - 4 PM")
    private String availability;

    private Integer available;

    @Schema(example = "1111111111")
    @NotEmpty(message = "Enter Phone Number")
    private String phoneNumber;

    @Schema(example = "testdoctor@gmail.com")
    private String email;
}
