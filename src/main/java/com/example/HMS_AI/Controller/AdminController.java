package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.AdminUpdateDTO;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.AdminServics;
import com.example.HMS_AI.Service.DoctorService;
import com.example.HMS_AI.Service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminServics adminServics;
    private final DoctorService doctorService;
    private final PatientService patientService;
    public AdminController(AdminServics adminServics,
                           DoctorService doctorService,
                           PatientService patientService){
        this.adminServics = adminServics;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/getAdminDetails")
    public ResponseEntity<GlobalResponseHandler> getAdminDetails(HttpServletRequest request){
        return adminServics.getAdminDetails(request.getHeader("Authorization"));
    }

    @PutMapping("/editAdminDetails")
    public ResponseEntity<GlobalResponseHandler> updateAdmin(@Valid @RequestBody AdminUpdateDTO dto, HttpServletRequest request){
        return adminServics.updateAdmin(dto,request.getHeader("Authorization"));
    }
    @PostMapping("/register/doctor")
    public ResponseEntity<GlobalResponseHandler> registerDoctor(@Valid @RequestBody UserDto dto){
        return doctorService.registerDoctor(dto);
    }
    @PostMapping("/register/patient")
    public ResponseEntity<GlobalResponseHandler> registerPatient(@Valid @RequestBody UserDto dto) {
        return patientService.registerPatient(dto);
    }


}
