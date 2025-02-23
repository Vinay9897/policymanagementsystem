package com.policymanagement.dtos;

import com.policymanagement.enums.Gender;
import com.policymanagement.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

	@NotBlank(message = "FirstName is Required")
	@Size(min = 2)
	private String firstName;

	@NotBlank(message = "LastName is Required")
	@Size(min = 2)
	private String lastName;

	@NotNull(message = "Age is required")
	private Integer age;

	@NotNull(message = "Gender is required")
	private Gender gender;

	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "^\\d{10}$", message = "Contact number must be 10 digits")
	private String contactNumber;

	@NotNull(message = "GmailId is required")
	private String gmailId;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@NotNull(message = "User role is required")
	private UserRole role;
}
