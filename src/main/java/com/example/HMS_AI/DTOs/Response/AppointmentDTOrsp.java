package com.example.HMS_AI.DTOs.Response;

import com.example.HMS_AI.Enum.AppointmentStatus;


import java.sql.Time;
import java.util.Date;

public record AppointmentDTOrsp(
        Integer id,
        DoctorDTO doctor,
        PatientDTO patient,
        Date date,
        Time time,
        AppointmentStatus status) {
}
