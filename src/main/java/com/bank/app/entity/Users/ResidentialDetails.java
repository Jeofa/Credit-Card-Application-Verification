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
public class ResidentialDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resident_id;
	
	
	@Column(name="County")
	private String county;
	
	@Column(name="Consituency")
	private String constituency;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="BoxNumber")
	private String boxNo;
	
	@Column(name="PostalCode")
	private String postalCode;
	
	@OneToOne(mappedBy = "residence")
	private PersonalDetails residence;
	
	public ResidentialDetails() {
		
	}

	public int getResident_id() {
		return resident_id;
	}

	public void setResident_id(int resident_id) {
		this.resident_id = resident_id;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public PersonalDetails getResidence() {
		return residence;
	}

	public void setResidence(PersonalDetails residence) {
		this.residence = residence;
	}
	
	

	
}
