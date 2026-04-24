package com.example.HMS_AI.DTOs.Response;


public record DoctorDTO(
        Integer id ,
        String fullName,
        String specialization,
        String experience,
        String availability,
        String phoneNumber,
        String email) {

}
