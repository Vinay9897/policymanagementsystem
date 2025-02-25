package com.policymanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanagement.authentication.AuthenticationService;
import com.policymanagement.authentication.JWTService;
import com.policymanagement.dtos.LoginUserDto;
import com.policymanagement.dtos.UserRegistrationDto;
import com.policymanagement.entities.User;
import com.policymanagement.ifaceimpl.UserService;
import com.policymanagement.responses.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final JWTService jwtService;

	private final AuthenticationService authenticationService;

	public UserController(JWTService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
		User registeredUser = userService.registerUser(registrationDto);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());


		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping
    @PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{userId}")
	@Operation(summary = "Update user", description = "Update an existing user's information")
	public ResponseEntity<User> updateUser(@PathVariable Long userId,
			@Valid @RequestBody UserRegistrationDto updateDto) {
		User updatedUser = userService.updateUser(userId, updateDto);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Delete user", description = "Delete an existing user")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
