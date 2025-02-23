package com.policymanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policymanagement.entities.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	
	Optional<Policy> findByPolicyId(String policyId);

	boolean existsByPolicyId(String policyId);

	List<Policy> findByCompanyName(String companyName);
}
