package com.example.HMS_AI.Service;

import com.example.HMS_AI.Component.JwtUtility;
import com.example.HMS_AI.DTOs.Request.AdminUpdateDTO;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Entity.AdminEntity;
import com.example.HMS_AI.Repository.AdminRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServics {
    private final JwtUtility jwtUtility;
    private final AdminRepository adminRepository;
    public AdminServics(JwtUtility jwtUtility,
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
                .statusCode(HttpStatusCode.valueOf(200)).build());
    }
    public ResponseEntity<GlobalResponseHandler> updateAdmin(AdminUpdateDTO dto, String authorization) {
        String token = authorization.substring(7);
        String userName = jwtUtility.extractUserName(token);
        AdminEntity admn = adminRepository.findByName(userName).get();
        admn.setFullName(dto.getFullName());
        admn.setPhone(dto.getPhone());
        if (dto.getEmail()!=null){
            admn.setEmail(dto.getEmail());
        }
        if (dto.getAddress()!=null){
            admn.setAddress(dto.getAddress());
        }
        adminRepository.save(admn);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("Admin Updates Successfully")
                .statusCode(HttpStatusCode.valueOf(200))
                .build());
    }
}
