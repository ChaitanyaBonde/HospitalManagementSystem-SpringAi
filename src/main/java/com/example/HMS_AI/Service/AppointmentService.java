package com.example.HMS_AI.Service;

import com.example.HMS_AI.Common.CustomException.ResourceNotFoundException;
import com.example.HMS_AI.DTOs.Request.AppointmentDTO;
import com.example.HMS_AI.DTOs.Request.AppointmentRecordDTO;
import com.example.HMS_AI.DTOs.Response.AppointmentDTOrsp;
import com.example.HMS_AI.DTOs.Response.DoctorDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.DTOs.Response.PatientDTO;
import com.example.HMS_AI.Entity.*;
import com.example.HMS_AI.Enum.AppointmentStatus;
import com.example.HMS_AI.Enum.ReciverEnum;
import com.example.HMS_AI.Enum.RequestStatus;
import com.example.HMS_AI.Repository.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    public AppointmentService(DoctorRepository doctorRepository,
                              PatientRepository patientRepository,
                              PrescriptionRepository prescriptionRepository,
                              MedicalRecordRepository medicalRecordRepository,
                              AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.prescriptionRepository = prescriptionRepository;
    }


    public ResponseEntity<GlobalResponseHandler> scheduleAppointment(@Valid AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        Appointment appointment = new Appointment();
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus(AppointmentStatus.REQUESTED);
        appointmentRepository.save(appointment);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Appointment Scheduled")
                .status(RequestStatus.SUCCESS).build());
    }

    public ResponseEntity<GlobalResponseHandler> editAppointmentStatus(String id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(Integer.valueOf(id)).orElseThrow(()->new ResourceNotFoundException("Appointment Not Found"));
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Appointment Status Updated.")
                .status(RequestStatus.SUCCESS).build());
    }

    public ResponseEntity<GlobalResponseHandler> getAppointment(ReciverEnum receiver, String id, AppointmentStatus status) {
        List<AppointmentDTOrsp> returnList = new ArrayList<>();
        if (receiver.equals(ReciverEnum.DOCTOR)){
            List<Appointment> appointmentList = appointmentRepository.findAllByDoctorIdAndStatus(Integer.valueOf(id),status);
            if (!appointmentList.isEmpty()) {
                for (Appointment apt : appointmentList) {
                    returnList.add(new AppointmentDTOrsp(
                            apt.getId(),
                            new DoctorDTO(apt.getDoctor().getId(),
                                    apt.getDoctor().getFullName(),
                                    apt.getDoctor().getSpecialization(),
                                    apt.getDoctor().getExperience(),
                                    apt.getDoctor().getAvailability(),
                                    apt.getDoctor().getPhoneNumber(),
                                    apt.getDoctor().getEmail()),
                            new PatientDTO(
                                    apt.getPatient().getId(),
                                    apt.getPatient().getEmail(),
                                    apt.getPatient().getFullName(),
                                    apt.getPatient().getAge(),
                                    apt.getPatient().getBloodGroup(),
                                    apt.getPatient().getPhoneNumber()),
                            apt.getDate(),
                            apt.getTime(),
                            apt.getStatus()));
                }
                return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                        .status(RequestStatus.SUCCESS)
                        .message("Appointment fetched successfully")
                        .data(returnList).build());
            }else
                return ResponseEntity.status(201).body(GlobalResponseHandler.builder()
                        .status(RequestStatus.FAILED)
                    .message("No appointment found for doctor").build());
        }else if (receiver.equals(ReciverEnum.PATIENT)) {
            List<Appointment> appointmentList = appointmentRepository.findAllByPatientIdAndStatus(Integer.valueOf(id),status);
            if (!appointmentList.isEmpty()) {
                for (Appointment apt : appointmentList) {
                       returnList.add(new AppointmentDTOrsp(
                            apt.getId(),
                            new DoctorDTO(apt.getDoctor().getId(),
                                    apt.getDoctor().getFullName(),
                                    apt.getDoctor().getSpecialization(),
                                    apt.getDoctor().getExperience(),
                                    apt.getDoctor().getAvailability(),
                                    apt.getDoctor().getPhoneNumber(),
                                    apt.getDoctor().getEmail()),
                            new PatientDTO(
                                    apt.getPatient().getId(),
                                    apt.getPatient().getEmail(),
                                    apt.getPatient().getFullName(),
                                    apt.getPatient().getAge(),
                                    apt.getPatient().getBloodGroup(),
                                    apt.getPatient().getPhoneNumber()),
                            apt.getDate(),
                            apt.getTime(),
                            apt.getStatus()));
                }
                return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                        .status(RequestStatus.SUCCESS)
                        .message("Appointment fetched successfully")
                        .data(returnList).build());
            }else
                return ResponseEntity.status(201).body(GlobalResponseHandler.builder()
                        .status(RequestStatus.FAILED)
                        .message("No appointment found for patient").build());
        }
        return ResponseEntity.status(405).body(GlobalResponseHandler.builder()
                .status(RequestStatus.FAILED)
                .message("Enter Correct Receiver").build());
    }

    public ResponseEntity<GlobalResponseHandler> completeAppointment( AppointmentRecordDTO dto){
        MedicalRecord medicalRecord = new MedicalRecord();
        Prescription prescription = new Prescription();
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow(() -> new ResourceNotFoundException("Appoint not found"));
        Patient patient = patientRepository.findById(appointment.getPatient().getId()).orElseThrow(() -> new ResourceNotFoundException("Something went wrong"));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
        prescription.setAppointment(appointment);
        prescription.setNotes(dto.getNotes());
        prescription.setMedicine(dto.getMedicines());
        prescription.setDate(new Date());
        prescriptionRepository.save(prescription);

        medicalRecord.setDate(new Date());
        medicalRecord.setReport(dto.getHealthReports());
        medicalRecord.setPrescription(prescription);
        medicalRecord.setPatient(patient);
        medicalRecordRepository.save(medicalRecord);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Appointment status updated successfully")
                .status(RequestStatus.SUCCESS)
                .build());
    }
}
