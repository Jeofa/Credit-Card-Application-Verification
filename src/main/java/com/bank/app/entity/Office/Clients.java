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
public class Clients {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int client_id;
	
	private String name;
	private String email;
	private String phone;
	private double salary;
	private String identity;
	private String employment;
	private String location;
	
	public Clients() {
		
	}
	
	

	public Clients(String name, String email, String phone, double salary, String identity, String employment,
			String location) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.identity = identity;
		this.employment = employment;
		this.location = location;
	}



	public int getclient_id() {
		return client_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public double getSalary() {
		return salary;
	}

	public String getIdentity() {
		return identity;
	}

	public String getEmployment() {
		return employment;
	}

	public String getLocation() {
		return location;
	}

}
