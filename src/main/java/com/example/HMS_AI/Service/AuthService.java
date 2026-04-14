package com.example.HMS_AI.Service;

import com.example.HMS_AI.Component.JwtUtility;
import com.example.HMS_AI.DTOs.Request.LoginRequest;
import com.example.HMS_AI.DTOs.Request.UserDto;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Entity.AdminEntity;
import com.example.HMS_AI.Entity.User;
import com.example.HMS_AI.Enum.UserRole;
import com.example.HMS_AI.Repository.AdminRepository;
import com.example.HMS_AI.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class AuthService {
    private final JwtUtility jwtUtility;
    private final UserDetailsServiceIMPL userDetailsServiceIMPL;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    public AuthService(JwtUtility jwtUtility,
                                    UserDetailsServiceIMPL userDetailsServiceIMPL,
                                    UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AdminRepository adminRepository){
        this.jwtUtility = jwtUtility;
        this.userDetailsServiceIMPL = userDetailsServiceIMPL;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @Value("${admin.secret}")
    String AdminSecretKey;

    public ResponseEntity<GlobalResponseHandler> GlobalLogin(LoginRequest loginRequest){
        UserDetails userDetails = userDetailsServiceIMPL.loadUserByUsername(loginRequest.getUserName());
        if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword()))
            return ResponseEntity.ok(GlobalResponseHandler.builder()
                    .data(jwtUtility.generateToken(userDetails))
                    .statusCode(HttpStatusCode.valueOf(200))
                    .message("Login Successully")
                    .build());
        else
            return ResponseEntity
                    .status(HttpStatus.valueOf(401))
                    .body(GlobalResponseHandler.builder()
                    .message("Enter Correct Password")
                    .statusCode(HttpStatusCode.valueOf(401))
                    .build());
    }

    public ResponseEntity<GlobalResponseHandler> adminRegister(UserDto loginRequest, HttpServletRequest request) {
        String apikey = request.getHeader("SEC-API-KEY");
        if ( apikey == null  || !apikey.equals(AdminSecretKey))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalResponseHandler
                    .builder()
                    .message("unauthorise request")
                    .statusCode(HttpStatusCode.valueOf(401))
                    .build());
        Optional<User> user = userRepository.findByNameOrEmail(loginRequest.getUserName(), loginRequest.getEmail());
        if (user.isPresent()){
            String message = user.get().getName().equals(loginRequest.getUserName()) ? "Admin with User name already exist" :
                    "Admin with Email id already exist";
            return ResponseEntity
                    .status(HttpStatus.valueOf(409))
                    .body(GlobalResponseHandler
                            .builder()
                            .message(message)
                            .statusCode(HttpStatus.CONFLICT)
                            .build());
        }
        User newUser = new User();
        newUser.setName(loginRequest.getUserName());
        newUser.setEmail(loginRequest.getEmail());
        newUser.setRole(UserRole.ADMIN);
        newUser.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        userRepository.save(newUser);
        AdminEntity admin = new AdminEntity();
        admin.setName(newUser.getName());
        admin.setEmail(newUser.getEmail());
        admin.setUser(newUser);
        adminRepository.save(admin);
        return ResponseEntity.ok().body(GlobalResponseHandler.builder()
                .message("admin Registered successfully")
                .statusCode(HttpStatus.valueOf(200)).build());

    }
}
