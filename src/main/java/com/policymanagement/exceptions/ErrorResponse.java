package com.policymanagement.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    
    // Constructor, getters, setters
}
