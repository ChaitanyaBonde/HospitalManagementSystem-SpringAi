package com.example.HMS_AI.DTOs.Request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class AppointmentDTO {
    @NotNull(message = "Enter Doctor Id")
    @Min(1)
    private Integer doctorId;
    @NotNull(message = "Enter Patient Id")
    @Min(1)
    private Integer patientId;

    private Date date;

    private Time time;
}

