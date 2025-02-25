package com.policymanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanagement.dtos.PolicyRegistrationDto;
import com.policymanagement.entities.Policy;
import com.policymanagement.exceptions.PolicyNotFoundException;
import com.policymanagement.ifaceimpl.PolicyService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    
    @Autowired
    private PolicyService policyService;
    
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Policy> registerPolicy(@Valid @RequestBody PolicyRegistrationDto registrationDto) {
        Policy registeredPolicy = policyService.registerPolicy(registrationDto);
        return new ResponseEntity<>(registeredPolicy, HttpStatus.CREATED);
    }
    
    @PutMapping("/{policyId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Policy> updatePolicy(
            @PathVariable String policyId,
            @Valid @RequestBody PolicyRegistrationDto updateDto) {
        Policy updatedPolicy = policyService.updatePolicy(policyId, updateDto);
        return ResponseEntity.ok(updatedPolicy);
    }
    
    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicy(@PathVariable String policyId) {
        Policy policy = policyService.findByPolicyId(policyId)
            .orElseThrow(() -> new PolicyNotFoundException("Policy not found with ID: " + policyId));
        return ResponseEntity.ok(policy);
    }
    
    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all policies", description = "Retrieve a list of all policies")
    public ResponseEntity<List<Policy>> getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }
    
    @DeleteMapping("/{policyId}")
//    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete policy", description = "Delete an existing policy by ID")
    public ResponseEntity<Void> deletePolicy(@PathVariable String policyId) {
        policyService.deletePolicy(policyId);
        return ResponseEntity.noContent().build();
    }
}
