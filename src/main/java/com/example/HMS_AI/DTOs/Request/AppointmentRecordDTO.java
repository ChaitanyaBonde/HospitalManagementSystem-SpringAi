package com.example.HMS_AI.DTOs.Request;

import lombok.Data;

@Data
public class AppointmentRecordDTO {
    private String healthReports;
    private Integer appointmentId;
    private String medicines;
    private String notes;


}
