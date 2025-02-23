package com.policymanagement.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String policyId;
    
    @Column(nullable = false)
    private String policyName;
    
    @Column(nullable = false)
    private String companyName;
    
    @Column(nullable = false)
    private Integer noOfMonths;
    
    @Column(unique = true, nullable = false)
    private String policyType;

    // Audit fields
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private String createdBy;
    
    @Column
    private LocalDateTime updatedAt;
    
    @Column
    private String updatedBy;

   
}
