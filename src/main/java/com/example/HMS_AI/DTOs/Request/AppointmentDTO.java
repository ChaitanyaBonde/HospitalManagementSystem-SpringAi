package com.example.HMS_AI.DTOs.Request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class AppointmentDTO {
    @Schema(example = "1")
    @NotNull(message = "Enter Doctor Id")
    @Min(1)
    private Integer doctorId;

    @Schema(example = "1")
    @NotNull(message = "Enter Patient Id")
    @Min(1)
    private Integer patientId;

    @Schema(example = "2026-04-25")
    private Date date;

    @Schema(example = "10:30:00")
    private Time time;
}

