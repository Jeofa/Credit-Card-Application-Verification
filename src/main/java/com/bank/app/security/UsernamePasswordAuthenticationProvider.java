package com.bank.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bank.app.service.AdminService;
import com.bank.app.security.*;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		var user = userService.loadUserByUsername(username);
		if(user != null) {
			if(passwordEncoder.matches(password, user.getPassword())) {
				return new UsernamePasswordAuthentication(username, password, user.getAuthorities());
			}
		}
		throw new BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthentication.class.equals(authentication);
	}
	

}
