package com.example.HMS_AI.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_patient")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer age;
    private String bloodGroup;
    private String medicalHistory;
}
