package com.policymanagement.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
	
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }
}
