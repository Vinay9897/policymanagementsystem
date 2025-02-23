package com.policymanagement.exceptions;

public class PolicyNotFoundException extends RuntimeException {
	public PolicyNotFoundException(String message) {
		super(message);
	}
}