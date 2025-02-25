package com.policymanagement.authentication;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.policymanagement.entities.User;
import com.policymanagement.repositories.UserRepository;

public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByGmailId(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

	
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

		// Return UserDetails object
		return new org.springframework.security.core.userdetails.User(user.getGmailId(), 
				user.getPassword(), true, 
				true, 
				true, 
				true, 
				Collections.singletonList(authority));
	}
}
