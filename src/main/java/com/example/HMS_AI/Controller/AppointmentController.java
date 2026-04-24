package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.AppointmentDTO;
import com.example.HMS_AI.DTOs.Request.AppointmentRecordDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Enum.AppointmentStatus;
import com.example.HMS_AI.Service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<GlobalResponseHandler> scheduleAppointment(@Valid @RequestBody AppointmentDTO dto){
        return appointmentService.scheduleAppointment(dto);
    }

    @GetMapping("/getAppointmentBy/{receiver}/{id}/{status}")
    public ResponseEntity<GlobalResponseHandler> getAppointment(@PathVariable String receiver,
                                                                @PathVariable String id,
                                                                @PathVariable AppointmentStatus status){
        return appointmentService.getAppointment(receiver,id,status);
    }

    @PutMapping("editAppointmentStatus/{id}/{status}")
    public ResponseEntity<GlobalResponseHandler> editAppointmentStatus(@PathVariable String id,
                                                                       @PathVariable AppointmentStatus status){
        return appointmentService.editAppointmentStatus(id,status);
    }

    @PutMapping("completeAppointment")
    public ResponseEntity<GlobalResponseHandler> completeAppointment(@Valid @RequestBody AppointmentRecordDTO dto){
        return appointmentService.completeAppointment(dto);
    }

}
