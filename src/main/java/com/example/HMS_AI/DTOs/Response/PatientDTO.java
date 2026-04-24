package com.example.HMS_AI.DTOs.Response;

public record PatientDTO(
        Integer id,
        String email,
        String fullName,
        Integer age,
        String bloodGroup,
        String phoneNumber) {
}
