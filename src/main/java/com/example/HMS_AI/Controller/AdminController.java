package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.AdminUpdateDTO;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.AdminServices;
import com.example.HMS_AI.Service.DoctorService;
import com.example.HMS_AI.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@Tag(name = "2. Admin APIs")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final AdminServices adminServices;
    private final DoctorService doctorService;
    private final PatientService patientService;
    public AdminController(AdminServices adminServices,
                           DoctorService doctorService,
                           PatientService patientService){
        this.adminServices = adminServices;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Operation(summary = "Get Admin details")
    @GetMapping("/getAdminDetails")
    public ResponseEntity<GlobalResponseHandler> getAdminDetails(HttpServletRequest request){
        return adminServices.getAdminDetails(request.getHeader("Authorization"));
    }

    @Operation(summary = "Update Admin Details")
    @PutMapping("/editAdminDetails")
    public ResponseEntity<GlobalResponseHandler> updateAdmin(@Valid @RequestBody AdminUpdateDTO dto, HttpServletRequest request){
        return adminServices.updateAdmin(dto,request.getHeader("Authorization"));
    }

    @Operation(summary = "Register Doctor")
    @PostMapping("/register/doctor")
    public ResponseEntity<GlobalResponseHandler> registerDoctor(@Valid @RequestBody UserDto dto){
        return doctorService.registerDoctor(dto);
    }

    @Operation(summary = "Register Patient")
    @PostMapping("/register/patient")
    public ResponseEntity<GlobalResponseHandler> registerPatient(@Valid @RequestBody UserDto dto) {
        return patientService.registerPatient(dto);
    }


}
