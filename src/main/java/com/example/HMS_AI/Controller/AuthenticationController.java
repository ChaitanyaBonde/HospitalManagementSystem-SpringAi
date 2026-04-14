package com.example.HMS_AI.Controller;

import com.example.HMS_AI.Component.JwtUtility;
import com.example.HMS_AI.DTOs.Request.LoginRequest;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Entity.User;
import com.example.HMS_AI.Repository.UserRepository;
import com.example.HMS_AI.Service.AuthService;
import com.example.HMS_AI.Service.UserDetailsServiceIMPL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponseHandler> GlobalLogin(@Valid @RequestBody LoginRequest loginRequest){
        return authService.GlobalLogin(loginRequest);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<GlobalResponseHandler> adminRegister(@Valid @RequestBody UserDto loginRequest,
                                                               HttpServletRequest request) {
        return authService.adminRegister(loginRequest, request);

    }

}
