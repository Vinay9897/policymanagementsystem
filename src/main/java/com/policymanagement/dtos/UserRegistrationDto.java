package com.policymanagement.dtos;

import com.policymanagement.enums.Gender;
import com.policymanagement.enums.UserRole;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String contactNumber;
    private String userId;
    private String password;
    private UserRole role;
}
