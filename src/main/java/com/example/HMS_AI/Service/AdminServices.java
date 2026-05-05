package com.example.HMS_AI.Service;

import com.example.HMS_AI.Component.JwtUtility;
import com.example.HMS_AI.DTOs.Request.AdminUpdateDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Entity.AdminEntity;
import com.example.HMS_AI.Enum.RequestStatus;
import com.example.HMS_AI.Repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {
    private final JwtUtility jwtUtility;
    private final AdminRepository adminRepository;

    public AdminServices(JwtUtility jwtUtility,
                         AdminRepository adminRepository){
        this.jwtUtility = jwtUtility;
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<GlobalResponseHandler> getAdminDetails(String authorization) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        AdminEntity admin = adminRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Admin Not Foumd"));
        return ResponseEntity.ok(GlobalResponseHandler.builder()
                .message("Admin details ")
                .data(admin)
                .status(RequestStatus.SUCCESS).build());
    }
    public ResponseEntity<GlobalResponseHandler> updateAdmin(AdminUpdateDTO dto, String authorization) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        AdminEntity admin = adminRepository.findByName(userName).orElseThrow(() -> new RuntimeException("Admin Not Found"));
        admin.setFullName(dto.getFullName());
        admin.setPhone(dto.getPhone());
        if (dto.getEmail()!=null){
            admin.setEmail(dto.getEmail());
        }
        if (dto.getAddress()!=null){
            admin.setAddress(dto.getAddress());
        }
        adminRepository.save(admin);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Admin Updates Successfully")
                .status(RequestStatus.SUCCESS)
                .build());
    }
}
