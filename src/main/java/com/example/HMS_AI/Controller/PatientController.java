package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.PatientDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PutMapping("updatePatient")
    public ResponseEntity<GlobalResponseHandler> updatePatient(@Valid @RequestBody PatientDTO dto, HttpServletRequest request){
        return patientService.updatePatient(dto,request.getHeader("Authorization"));
    }
    @GetMapping("{id}")
    public ResponseEntity<GlobalResponseHandler> getPatient(@PathVariable String id){
        return patientService.getPatient(id);
    }
}
