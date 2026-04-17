package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Optional<Patient> findByName(String name);
}
