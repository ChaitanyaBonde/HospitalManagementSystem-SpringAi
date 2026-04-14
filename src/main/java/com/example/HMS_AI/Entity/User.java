package com.example.HMS_AI.Entity;

import com.example.HMS_AI.Enum.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;


}

