package com.example.HMS_AI.Entity;

import com.example.HMS_AI.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_appoinment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private Date date;
    private Time time;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
