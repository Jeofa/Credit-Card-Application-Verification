package com.bank.app.entity.Users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class OtherDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="HouseNumber")
	private String houseNo;
	
	@Column(name="LoanBal")
	private String loanBalances;
	
	@Column(name="BillingDate")
	private String billingDate;
	
	@Column(name="RefName")
	private String refName;
	
	@Column(name="RefEmail")
	private String refEmail;
	
	@Column(name="RefPhone")
	private String refPhoneNo;
	
	@OneToOne(mappedBy = "otherdetails")
	private PersonalDetails person;
	
	public OtherDetails() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getLoanBalances() {
		return loanBalances;
	}

	public void setLoanBalances(String loanBalances) {
		this.loanBalances = loanBalances;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefEmail() {
		return refEmail;
	}

	public void setRefEmail(String refEmail) {
		this.refEmail = refEmail;
	}

	public String getRefPhoneNo() {
		return refPhoneNo;
	}

	public void setRefPhoneNo(String refPhoneNo) {
		this.refPhoneNo = refPhoneNo;
	}

	public PersonalDetails getPerson() {
		return person;
	}

	public void setPerson(PersonalDetails person) {
		this.person = person;
	}
	
	
	
}
