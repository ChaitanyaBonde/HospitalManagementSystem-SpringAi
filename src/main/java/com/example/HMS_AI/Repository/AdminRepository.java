package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
    Optional<AdminEntity> findByName(String userName);
}
