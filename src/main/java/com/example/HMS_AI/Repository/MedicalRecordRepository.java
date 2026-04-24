package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer> {
}
