package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Optional<Doctor> findByName(String userName);
}
