package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.AppointmentDTO;
import com.example.HMS_AI.DTOs.Request.AppointmentRecordDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Enum.AppointmentStatus;
import com.example.HMS_AI.Enum.ReciverEnum;
import com.example.HMS_AI.Service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@Tag(name = "5. Appointment APIs")
@SecurityRequirement(name = "bearerAuth")

public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Schedule appointment")
    @PostMapping("/schedule")
    public ResponseEntity<GlobalResponseHandler> scheduleAppointment(@Valid @RequestBody AppointmentDTO dto){
        return appointmentService.scheduleAppointment(dto);
    }

    @Operation(summary = "Get appointment details by doctor or patient and status")
    @GetMapping("/getAppointmentBy/{receiver}/{id}/{status}")
    public ResponseEntity<GlobalResponseHandler> getAppointment(@PathVariable ReciverEnum receiver,
                                                                @PathVariable String id,
                                                                @PathVariable AppointmentStatus status){
        return appointmentService.getAppointment(receiver,id,status);
    }

    @Operation(summary = "Edit appointment status")
    @PutMapping("editAppointmentStatus/{id}/{status}")
    public ResponseEntity<GlobalResponseHandler> editAppointmentStatus(@PathVariable String id,
                                                                       @PathVariable AppointmentStatus status){
        return appointmentService.editAppointmentStatus(id,status);
    }

    @Operation(summary = "Mark appointment complete and add the necessary details")
    @PutMapping("completeAppointment")
    public ResponseEntity<GlobalResponseHandler> completeAppointment(@Valid @RequestBody AppointmentRecordDTO dto){
        return appointmentService.completeAppointment(dto);
    }

}
