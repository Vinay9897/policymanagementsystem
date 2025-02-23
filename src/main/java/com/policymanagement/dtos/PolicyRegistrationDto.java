package com.policymanagement.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyRegistrationDto {

	@NotBlank(message = "Policy Type is required")
	private String policyType;

	@NotBlank(message = "Policy name is required")
	private String policyName;

	@NotBlank(message = "Company name is required")
	private String companyName;

	@Min(value = 1, message = "Number of months must be at least 1")
	private Integer noOfMonths;

}
