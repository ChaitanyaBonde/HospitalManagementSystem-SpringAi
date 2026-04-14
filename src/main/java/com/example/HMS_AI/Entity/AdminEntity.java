package com.example.HMS_AI.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tblAdmin")
@Data
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String fullName;

}
