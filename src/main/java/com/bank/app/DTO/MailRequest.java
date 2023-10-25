package com.bank.app.DTO;

public class MailRequest {
	
	private String empname;
	private int empid;
	private String empno;
	private String to;
	private String from;
	private String subject;
	
	
	public MailRequest() {
		
	}

	

	public String getEmpname() {
		return empname;
	}



	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public int getEmpid() {
		return empid;
	}



	public void setEmpid(int id) {
		this.empid = id;
	}



	public String getEmpno() {
		return empno;
	}



	public void setEmpno(String empno) {
		this.empno = empno;
	}



	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
}
