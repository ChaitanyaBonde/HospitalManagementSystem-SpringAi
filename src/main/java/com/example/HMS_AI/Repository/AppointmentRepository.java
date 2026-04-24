package com.example.HMS_AI.Repository;

import com.example.HMS_AI.Entity.Appointment;
import com.example.HMS_AI.Enum.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findAllByDoctorIdAndStatus(Integer id, AppointmentStatus status);
    List<Appointment> findAllByPatientIdAndStatus(Integer id,AppointmentStatus status);
}
