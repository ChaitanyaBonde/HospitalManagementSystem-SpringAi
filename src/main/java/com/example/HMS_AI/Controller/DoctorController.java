package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.DoctorUpdateDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@Tag(name = "3. Doctor APIs")
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @Operation(summary = "Update doctor information")
    @PutMapping("/updateDoctor")
    public ResponseEntity<GlobalResponseHandler> updateDoctor(@Valid @RequestBody DoctorUpdateDTO dto,
                                                              HttpServletRequest request){
        return doctorService.updateDoctor(dto,request.getHeader("Authorization"));
    }

    @Operation(summary = "Make doctor active")
    @PatchMapping("/makeDoctorActive/{status}")
    public ResponseEntity<GlobalResponseHandler> makeDoctorActive(@PathVariable String status,
                                                                  HttpServletRequest request){
        return doctorService.makeDoctorActive(request.getHeader("Authorization"), status);
    }

    @Operation(summary = "Get doctor information")
    @GetMapping()
    public ResponseEntity<GlobalResponseHandler> getDoctor(@RequestParam(required = false) String doctorId,
                                                           HttpServletRequest request){
        return doctorService.getDoctor(doctorId,request.getHeader("Authorization"));
    }


}
