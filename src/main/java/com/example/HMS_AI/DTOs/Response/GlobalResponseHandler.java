package com.example.HMS_AI.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResponseHandler {
    private Object data;
    private String message;
    private HttpStatusCode statusCode;
}
