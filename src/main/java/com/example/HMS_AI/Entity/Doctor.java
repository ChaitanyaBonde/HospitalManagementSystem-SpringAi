package com.example.HMS_AI.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tbl_doctor")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String fullName;
    private String specialization;
    private String experience;
    private String availability;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Integer available;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

}
