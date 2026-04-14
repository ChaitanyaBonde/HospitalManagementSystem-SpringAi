package com.example.HMS_AI.Entity;

import com.fasterxml.jackson.annotation.JacksonInject;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appoinment appoinment;

    private String medicine;
    private String notes;
    private Date date;
}
