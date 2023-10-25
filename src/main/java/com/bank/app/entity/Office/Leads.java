package com.bank.app.entity.Office;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
public class Leads {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lead_id;
	
	private String name;
	private String email;
	private String phone;
	private double salary;
	private int identity;
	private String employment;
	private String location;
	private String status;
	
	public Leads() {
		
	}
	
	

	public Leads(String name, String email, String phone, double salary, int identity, String employment, 
			String location,String status) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.identity = identity;
		this.employment = employment;
		this.location = location;
		this.status = status;
	}



	public int getLead_id() {
		return lead_id;
	}



	public void setLead_id(int lead_id) {
		this.lead_id = lead_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public String getEmployment() {
		return employment;
	}



	public void setEmployment(String employment) {
		this.employment = employment;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}




	
	
	
	
}
