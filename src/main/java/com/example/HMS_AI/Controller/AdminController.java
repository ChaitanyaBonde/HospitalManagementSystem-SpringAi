package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.AdminUpdateDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.AdminServics;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminServics adminServics;
    public AdminController(AdminServics adminServics){
        this.adminServics = adminServics;
    }

    @GetMapping("/getAdminDetails")
    public ResponseEntity<GlobalResponseHandler> getAdminDetails(HttpServletRequest request){
        return adminServics.getAdminDetails(request.getHeader("Authorization"));
    }

    @PutMapping("/editAdminDetails")
    public ResponseEntity<GlobalResponseHandler> updateAdmin(@Valid @RequestBody AdminUpdateDTO dto, HttpServletRequest request){
        return adminServics.updateAdmin(dto,request.getHeader("Authorization"));
    }
}
