package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.DoctorUpdateDTO;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping("register")
    public ResponseEntity<GlobalResponseHandler> registerDoctor(@Valid @RequestBody UserDto dto){
        return doctorService.registerDoctor(dto);
    }

    @PutMapping("updateDoctor")
    public ResponseEntity<GlobalResponseHandler> updateDoctor(@Valid @RequestBody DoctorUpdateDTO dto, HttpServletRequest request){
        return doctorService.updateDoctor(dto,request.getHeader("Authorization"));
    }

    @PatchMapping("makeDoctorActive/{status")
    public ResponseEntity<GlobalResponseHandler> makeDoctorActive(@PathVariable String status,
                                                                  HttpServletRequest request){
        return doctorService.makeDoctorActive(request.getHeader("Authorizationn"), status);
    }


}
