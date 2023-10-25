package com.bank.app.model;

public class PaySlip {

	private String name;
	private String identity;
	private String email;
	private String netpay;
	
	public PaySlip() {
		
	}
	public PaySlip(String name, String identity, String email, String netpay) {
		super();
		this.name = name;
		this.identity = identity;
		this.email = email;
		this.netpay = netpay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNetpay() {
		return netpay;
	}
	public void setNetpay(String netpay) {
		this.netpay = netpay;
	}
	
	
	
	
}
