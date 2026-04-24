package com.example.HMS_AI.Service;

import com.example.HMS_AI.Common.CustomException.ResourceNotFoundException;
import com.example.HMS_AI.Component.JwtUtility;
import com.example.HMS_AI.DTOs.Request.DoctorUpdateDTO;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.DoctorDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Entity.Doctor;
import com.example.HMS_AI.Entity.User;
import com.example.HMS_AI.Enum.UserRole;
import com.example.HMS_AI.Repository.DoctorRepository;
import com.example.HMS_AI.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    private final JwtUtility jwtUtility;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(JwtUtility jwtUtility,
                         DoctorRepository doctorRepositoryt,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder){
        this.doctorRepository = doctorRepositoryt;
        this.jwtUtility = jwtUtility;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<GlobalResponseHandler> registerDoctor(UserDto dto) {
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
        newUser.setRole(UserRole.DOCTOR);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(newUser);
        Doctor doctor = new Doctor();
        doctor.setName(newUser.getName());
        doctor.setEmail(newUser.getEmail());
        doctor.setUser(newUser);
        doctorRepository.save(doctor);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Doctor Registered successfully")
                .statusCode(HttpStatus.valueOf(200)).build());

    }

    public ResponseEntity<GlobalResponseHandler> updateDoctor(DoctorUpdateDTO dto, String authorization) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        Doctor doctor ;
        if (dto.getDoctorId()!=null)
            doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(()-> new ResourceNotFoundException("Doctor Not Found By Id"));
        else
            doctor = doctorRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found, Login Again"));

        doctor.setFullName(dto.getFullName());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail()!=null){
            doctor.setEmail(dto.getEmail());
        }
        if (dto.getAvailability()!=null){
            doctor.setAvailability(dto.getAvailability());
        }
        if (dto.getExperience()!=null){
            doctor.setExperience(dto.getExperience());
        }
        if (dto.getSpecialization()!=null){
            doctor.setSpecialization(dto.getSpecialization());
        }
        doctorRepository.save(doctor);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Doctor Updates Successfully")
                .statusCode(HttpStatusCode.valueOf(200))
                .build());
    }

    public ResponseEntity<GlobalResponseHandler> makeDoctorActive(String authorization , String status) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        Doctor doctor = doctorRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found with name"));
        doctor.setAvailable(Integer.valueOf(status));
        doctorRepository.save(doctor);
        return ResponseEntity.ok(GlobalResponseHandler.builder().statusCode(HttpStatus.OK).build());
    }

    public ResponseEntity<GlobalResponseHandler> getDoctor(String id,String authorization) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        Doctor doctor;
        if (id!=null)
            doctor = doctorRepository.findById(Integer.valueOf(id)).orElseThrow(()-> new ResourceNotFoundException("Doctor Not Found"));
        else
            doctor = doctorRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));

        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Doctor Details Fetched")
                .statusCode(HttpStatus.valueOf(200))
                .data(new DoctorDTO(doctor.getId(),
                        doctor.getFullName(),
                        doctor.getSpecialization(),
                        doctor.getExperience(),
                        doctor.getAvailability(),
                        doctor.getPhoneNumber(),
                        doctor.getEmail()))
                .build());
    }
}
