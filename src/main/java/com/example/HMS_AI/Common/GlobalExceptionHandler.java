package com.example.HMS_AI.Common;

import com.example.HMS_AI.Common.CustomException.ResourceNotFoundException;
import com.example.HMS_AI.DTOs.Response.GlobalResponseHandler;
import com.example.HMS_AI.Enum.RequestStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseHandler> ExceptionHandler(Exception ex, HttpServletRequest request){
        log.error("Error At path :: {}", request.getRequestURI());
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(GlobalResponseHandler.builder()
                .message("Something Went Wrong : "+ex.getMessage())
                .status(RequestStatus.FAILED).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseHandler> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getDefaultMessage()));
        return ResponseEntity.status(400).body(GlobalResponseHandler.builder()
                .message(errors.toString().substring(1,errors.toString().length()-1)).build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalResponseHandler> handleResourceNotFoundExceptions(ResourceNotFoundException ex, HttpServletRequest request){
        log.error("Resource Not Found At path :: {}", request.getRequestURI());
        ex.printStackTrace();
        return ResponseEntity.status(404).body(GlobalResponseHandler.builder()
                .message(ex.getMessage())
                .status(RequestStatus.FAILED).build());
    }
}
