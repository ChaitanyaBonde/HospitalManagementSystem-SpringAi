package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByName(String userName);
    Optional<User> findByNameOrEmail(String userName, String email);
}
