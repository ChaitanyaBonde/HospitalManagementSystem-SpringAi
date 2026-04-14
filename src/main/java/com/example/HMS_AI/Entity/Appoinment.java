package com.example.HMS_AI.Entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_appoinment")
public class Appoinment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private Date date;
    private Time time;
    private Character status;
}
