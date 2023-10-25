package com.bank.app.entity.Users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
public class BankDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bank_id;
	
	@Column(name="BankName")
	private String bankName;
	
	@Column(name="BankAccount")
	private String backAccount;
	
	@Column(name="BankType")
	private String accType;
	
	@Column(name="BankStmt")
	private String bankStmt;
	
	@OneToOne(mappedBy = "my_bank")
	private PersonalDetails person;
	
	public BankDetails() {
		
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBackAccount() {
		return backAccount;
	}

	public void setBackAccount(String backAccount) {
		this.backAccount = backAccount;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBankStmt() {
		return bankStmt;
	}

	public void setBankStmt(String bankStmt) {
		this.bankStmt = bankStmt;
	}

	public PersonalDetails getPerson() {
		return person;
	}

	public void setPerson(PersonalDetails person) {
		this.person = person;
	}

	
}
