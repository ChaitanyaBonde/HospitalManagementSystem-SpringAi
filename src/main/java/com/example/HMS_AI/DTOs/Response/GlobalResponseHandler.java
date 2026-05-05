package com.example.HMS_AI.DTOs.Response;

import com.example.HMS_AI.Enum.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResponseHandler {
    private Object data;
    private String message;
    private RequestStatus status;
}
