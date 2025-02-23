package com.policymanagement.ifaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanagement.dtos.UserRegistrationDto;
import com.policymanagement.entities.User;
import com.policymanagement.exceptions.UserAlreadyExistsException;
import com.policymanagement.exceptions.UserNotFoundException;
import com.policymanagement.repositories.UserRepository;

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
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User updateUser(Long userId, UserRegistrationDto updateDto) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        
        validateUpdateUser(updateDto, existingUser);
        mapUserFromDto(existingUser, updateDto);
        
        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
        	existingUser.setPassword(updateDto.getPassword());
//        	existingUser.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        
        return userRepository.save(existingUser);
    }
    
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        userRepository.delete(user);
    }
    
    private void validateNewUser(UserRegistrationDto dto) {
        if (userRepository.existsByGmailId(dto.getGmailId())) {
            throw new UserAlreadyExistsException("User ID already exists: " + dto.getGmailId());
        }
    }
    
    private void validateUpdateUser(UserRegistrationDto dto, User existingUser) {
        if (!existingUser.getGmailId().equals(dto.getGmailId()) && 
            userRepository.existsByGmailId(dto.getGmailId())) {
            throw new UserAlreadyExistsException("User ID already exists: " + dto.getGmailId());
        }
    }
    
    private void mapUserFromDto(User user, UserRegistrationDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setContactNumber(dto.getContactNumber());
        user.setGmailId(dto.getGmailId());
        user.setRole(dto.getRole());
    }
}
