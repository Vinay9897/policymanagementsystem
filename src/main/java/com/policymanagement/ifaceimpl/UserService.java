package com.policymanagement.ifaceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanagement.dtos.UserRegistrationDto;
import com.policymanagement.entities.User;
import com.policymanagement.exceptions.UserAlreadyExistsException;
import com.policymanagement.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    public User registerUser(UserRegistrationDto registrationDto) {
        // Validate if user already exists
        if (userRepository.existsByGmailId(registrationDto.getGmailId())) {
            throw new UserAlreadyExistsException("User ID already exists: " + registrationDto.getGmailId());
        }
        
        // Create new user
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setAge(registrationDto.getAge());
        user.setGender(registrationDto.getGender());
        user.setContactNumber(registrationDto.getContactNumber());
        user.setGmailId(registrationDto.getGmailId());
        user.setPassword(registrationDto.getPassword());
        user.setRole(registrationDto.getRole());
        
        return userRepository.save(user);
    }
}
