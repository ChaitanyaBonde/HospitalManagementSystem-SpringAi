package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
}
