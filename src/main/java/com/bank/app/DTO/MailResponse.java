package com.bank.app.DTO;

public class MailResponse {
	
	private boolean status;
	private String message;

	public MailResponse() {
		
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
