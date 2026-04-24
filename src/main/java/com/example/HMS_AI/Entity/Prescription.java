package com.example.HMS_AI.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "tbl_prescription")
@Entity
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private String medicine;
    private String notes;
    private Date date;
}
