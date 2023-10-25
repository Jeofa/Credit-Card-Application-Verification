package com.bank.app.entity.Users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class EmploymentDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int emp_id;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "personalDetails", referencedColumnName = "id")
//	private PersonalDetails personalDetails;
	
	@OneToOne(mappedBy = "employment")
	private PersonalDetails person;


	@Column(name="EmploymentType")
	private String empType;
	
	@Column(name="BusinessName")
	private String bsName;
	
	@Column(name="Position")
	private String bsPosition;
	
	@Column(name="TermsOfEmpl")
	private String termsOfEmp;
	
	@Column(name="BoxNumber")
	private String postalAddress;
	
	@Column(name="PostalCode")
	private String postalCode;
	
	@Column(name="PostalTown")
	private String bsTown;
	
	@Column(name="PhoneNumber")
	private String telphone;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Salary")
	private double salary;
	
	@Column(name="Allowance")
	private double allowance;
	
	@Column(name="Payslip")
	private String payslip;
	
	
	
	public EmploymentDetails() {
		
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getBsName() {
		return bsName;
	}

	public void setBsName(String bsName) {
		this.bsName = bsName;
	}

	public String getBsPosition() {
		return bsPosition;
	}

	public void setBsPosition(String bsPosition) {
		this.bsPosition = bsPosition;
	}

	public String getTermsOfEmp() {
		return termsOfEmp;
	}

	public void setTermsOfEmp(String termsOfEmp) {
		this.termsOfEmp = termsOfEmp;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getBsTown() {
		return bsTown;
	}

	public void setBsTown(String bsTown) {
		this.bsTown = bsTown;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public String getPayslip() {
		return payslip;
	}

	public void setPayslip(String payslip) {
		this.payslip = payslip;
	}
	
	public PersonalDetails getPersonalDetails() {
		return person;
	}
	
	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.person = personalDetails;
	}

	

	
	
	
	
	

}
