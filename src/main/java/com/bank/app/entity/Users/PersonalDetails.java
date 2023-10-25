package com.bank.app.entity.Users;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



@Entity
public class PersonalDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	

	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="card")
	private Card mycard;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="residence")
	private ResidentialDetails residence;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="details")
	private OtherDetails otherdetails;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="mybank")
	private BankDetails my_bank;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="employment")
	private EmploymentDetails employment;
	
	
	@Column(name="person_Id")
	private int person_Id;
	
	
	@Column(name="FirstName")
	private String fname;
	
	@Column(name="MiddleName")
	private String mname;
	
	@Column(name="LastName")
	private String lname;
	
	@Column(name="DateOfBirth")
	private String dob;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="MaritalStatus")
	private String marital;
	
	@Column(name="Imagename")
	private String id_img;
	
	public PersonalDetails() {
		
	}

	public int getPerson_Id() {
		return person_Id;
	}

	public void setPerson_Id(int person_Id) {
		this.person_Id = person_Id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getId_img() {
		return id_img;
	}

	public void setId_img(String id_img) {
		this.id_img = id_img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Card getMycard() {
		return mycard;
	}

	public void setMycard(Card mycard) {
		this.mycard = mycard;
	}



	public ResidentialDetails getResidence() {
		return residence;
	}

	public void setResidence(ResidentialDetails residence) {
		this.residence = residence;
	}

	public OtherDetails getOtherdetails() {
		return otherdetails;
	}

	public void setOtherdetails(OtherDetails otherdetails) {
		this.otherdetails = otherdetails;
	}

	public BankDetails getMy_bank() {
		return my_bank;
	}

	public void setMy_bank(BankDetails my_bank) {
		this.my_bank = my_bank;
	}

	public EmploymentDetails getEmployment() {
		return employment;
	}

	public void setEmployment(EmploymentDetails employment) {
		this.employment = employment;
	}

	
	
	
	
	
	
	
	
	
}
