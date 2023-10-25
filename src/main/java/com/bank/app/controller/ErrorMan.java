package com.bank.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorMan implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		Integer statusCode = null; 
		if(status != null) {
			statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value() ) {
				return "Error/404";
			}else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
				return "Error/401";
			}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "Error/500";
			}else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				return "Error/405";
			}
		}
		return "Error/error";
		
	}
	
	
	public String getErrorPath() {
		return "/error";
	}
	
}
