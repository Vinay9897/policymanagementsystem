package com.policymanagement.ifaceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanagement.dtos.PolicyRegistrationDto;
import com.policymanagement.entities.Policy;
import com.policymanagement.entities.User;
import com.policymanagement.exceptions.PolicyNotFoundException;
import com.policymanagement.repositories.PolicyRepository;

@Service
public class PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

//	@Autowired
//	private UserService userService; // For getting current user details

	public Policy registerPolicy(PolicyRegistrationDto registrationDto) {
		Policy policy = new Policy();
		policy.setPolicyId(generateUniquePolicyId());
		policy.setPolicyName(registrationDto.getPolicyName());
		policy.setCompanyName(registrationDto.getCompanyName());
		policy.setNoOfMonths(registrationDto.getNoOfMonths());
		policy.setPolicyType(registrationDto.getPolicyType());

		// Set audit fields
		policy.setCreatedAt(LocalDateTime.now());
		policy.setCreatedBy("user");
//	        policy.setCreatedBy(getCurrentUsername());

		return policyRepository.save(policy);
	}

	public Optional<Policy> findByPolicyId(String policyId) {
		return policyRepository.findByPolicyId(policyId);
	}

	public List<Policy> findByCompanyName(String companyName) {
		return policyRepository.findByCompanyName(companyName);
	}

	public List<Policy> getAllPolicies() {
		return policyRepository.findAll();
	}

	public Policy updatePolicy(String policyId, PolicyRegistrationDto updateDto) {
		Policy policy = policyRepository.findByPolicyId(policyId)
				.orElseThrow(() -> new PolicyNotFoundException("Policy not found with ID: " + policyId));

		policy.setPolicyName(updateDto.getPolicyName());
		policy.setCompanyName(updateDto.getCompanyName());
		policy.setNoOfMonths(updateDto.getNoOfMonths());
		policy.setUpdatedAt(LocalDateTime.now());
//	        policy.setUpdatedBy(getCurrentUsername());

		return policyRepository.save(policy);
	}

	private String generateUniquePolicyId() {
		// Generate a unique policy ID (e.g., POL-yyyy-MM-xxxxx)
		String baseId = "POL-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-"))
				+ String.format("%05d", new Random().nextInt(100000));

		// Ensure uniqueness
		while (policyRepository.existsByPolicyId(baseId)) {
			baseId = "POL-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-"))
					+ String.format("%05d", new Random().nextInt(100000));
		}

		return baseId;
	}

	public void deletePolicy(String policyId) {
		Policy policy = policyRepository.findByPolicyId(policyId)
				.orElseThrow(() -> new PolicyNotFoundException("Policy not found with ID: " + policyId));

		// Remove the policy from all associated users
//		for (User user : policy.getUsers()) {
//			user.removePolicy(policy);
//			userRepository.save(user);
//		}

		policyRepository.delete(policy);
	}

//	private String getCurrentUsername() {
//		// Implementation to get current authenticated username
//		return SecurityContextHolder.getContext().getAuthentication().getName();
//	}
}
