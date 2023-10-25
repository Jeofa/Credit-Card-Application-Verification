package com.bank.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private AuthenticationManager authManager;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
//		var a = customauthManager.authenticate(null);
//		if(a.isAuthenticated()) {
//			SecurityContextHolder.getContext().setAuthentication(a);
//		}
//		
		Authentication authentication = new UsernamePasswordAuthentication(username, password);
		
		Authentication auth =  authManager.authenticate(authentication);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/office/login");
	}
	
	
	
	

}
