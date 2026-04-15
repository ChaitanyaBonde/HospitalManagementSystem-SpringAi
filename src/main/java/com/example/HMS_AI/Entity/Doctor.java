package com.example.HMS_AI.Entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private Integer available;
    private String phoneNumber;
    private String email;

}
