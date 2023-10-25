package com.bank.app.entity.Admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	
	@Column(name="Role")
	private String role;
	
	@Column(name="RoleDesc")
	private String roleDesc;
	
	public Roles() {
		
	}

	public Roles(String role, String roleDesc) {
		super();
		this.role = role;
		this.roleDesc = roleDesc;
	}

	public int getRole_id() {
		return role_id;
	}

	public String getRole() {
		return role;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
	
	
	

}
