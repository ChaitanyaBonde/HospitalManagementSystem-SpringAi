package com.example.HMS_AI.Service;

import com.example.HMS_AI.Common.CustomException.ResourceNotFoundException;
import com.example.HMS_AI.Component.JwtUtility;
import com.example.HMS_AI.DTOs.Request.PatientDTO;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Entity.Patient;
import com.example.HMS_AI.Entity.User;
import com.example.HMS_AI.Enum.UserRole;
import com.example.HMS_AI.Repository.PatientRepository;
import com.example.HMS_AI.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final JwtUtility jwtUtility;
    public PatientService(PatientRepository patientRepository,
                          PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          JwtUtility jwtUtility){
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtility = jwtUtility;
    }

    public ResponseEntity<GlobalResponseHandler> registerPatient(UserDto dto) {
        Optional<User> user = userRepository.findByNameOrEmail(dto.getUserName(), dto.getEmail());
        if (user.isPresent()){
            String message = user.get().getName().equals(dto.getUserName()) ? "User with User name already exist" :
                    "User with Email id already exist";
            return ResponseEntity
                    .status(HttpStatus.valueOf(409))
                    .body(GlobalResponseHandler
                            .builder()
                            .message(message)
                            .statusCode(HttpStatus.CONFLICT)
                            .build());
        }
        User newUser = new User();
        newUser.setName(dto.getUserName());
        newUser.setEmail(dto.getEmail());
        newUser.setRole(UserRole.PATIENT);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(newUser);
        Patient patient = new Patient();
        patient.setName(newUser.getName());
        patient.setEmail(newUser.getEmail());
        patient.setUser(newUser);
        patientRepository.save(patient);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Patient Registered successfully")
                .statusCode(HttpStatus.valueOf(200)).build());

    }

    public ResponseEntity<GlobalResponseHandler> updatePatient(@Valid PatientDTO dto, String authorization) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        Patient patient;
        if (dto.getPatientId() != null)
            patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
        else
            patient = patientRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patient.setFullName(dto.getFullName());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAge(dto.getAge());
        patient.setBloodGroup(dto.getBloodGroup());
        patientRepository.save(patient);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Patient Updates Successfully")
                .statusCode(HttpStatusCode.valueOf(200))
                .build());
    }
    public ResponseEntity<GlobalResponseHandler> getPatient(String id , String authorization){
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        Patient patient;
        if (id != null)
            patient = patientRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
        else
            patient = patientRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Patient details fetched")
                .statusCode(HttpStatus.valueOf(200))
                .data(new com.example.HMS_AI.DTOs.Response.PatientDTO(
                        patient.getId(),
                        patient.getEmail(),
                        patient.getFullName(),
                        patient.getAge(),
                        patient.getBloodGroup(),
                        patient.getPhoneNumber()
                )).build());
    }
}
