package com.example.HMS_AI.Controller;

import com.example.HMS_AI.DTOs.Request.LoginRequest;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "1. Authentication API")
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService){
        this.authService = authService;
    }

    @Operation(summary = "Global login")
    @PostMapping("/login")
    public ResponseEntity<GlobalResponseHandler> GlobalLogin(@Valid @RequestBody LoginRequest loginRequest){
        return authService.GlobalLogin(loginRequest);
    }

    @Operation(summary = "Register Admin")
    @SecurityRequirement(name = "apiKeyAuth")
    @PostMapping("/admin/register")
    public ResponseEntity<GlobalResponseHandler> adminRegister(@Valid @RequestBody UserDto loginRequest,
                                                               HttpServletRequest request) {
        return authService.adminRegister(loginRequest, request);

    }

}
