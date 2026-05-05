package com.example.HMS_AI.DTOs.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppointmentRecordDTO {

    @Schema(example = "1. less RBCs, 2. heavy weight, 3. high cholesterol")
    private String healthReports;

    @Schema(example = "1")
    private Integer appointmentId;

    @Schema(example = "linearizing, zincMg")
    private String medicines;

    @Schema(example = "do weight loos exercise")
    private String notes;


}
