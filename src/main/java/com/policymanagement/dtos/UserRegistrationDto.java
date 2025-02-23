package com.policymanagement.dtos;

import com.policymanagement.enums.Gender;
import com.policymanagement.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String contactNumber;
    private String gmailId;
    private String password;
    private UserRole role;
}
