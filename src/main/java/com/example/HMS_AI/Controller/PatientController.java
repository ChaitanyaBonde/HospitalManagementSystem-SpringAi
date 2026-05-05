package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.PatientDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient")
@Tag(name = "4. Patient APIs")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @Operation(summary = "Update patient information")
    @PutMapping("updatePatient")
    public ResponseEntity<GlobalResponseHandler> updatePatient(@Valid @RequestBody PatientDTO dto, HttpServletRequest request){
        return patientService.updatePatient(dto,request.getHeader("Authorization"));
    }

    @Operation(summary = "Get patient details")
    @GetMapping()
    public ResponseEntity<GlobalResponseHandler> getPatient(@RequestParam(required = false) String patientId,
                                                            HttpServletRequest request){
        return patientService.getPatient(patientId,request.getHeader("Authorization"));
    }
}
