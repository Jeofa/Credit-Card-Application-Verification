package com.bank.app.entity.Office;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Logs {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int logs_id;
	
	private int person_id;
	
	private java.util.Date creation_date;
	
	private long application_duration;
	
	private String status;
	
	public Logs() {
		
	}
	
	

	public Logs(int person_id, java.util.Date creation, long l, String status) {
		super();
		this.person_id = person_id;
		this.creation_date = creation;
		this.application_duration = l;
		this.status = status;
	}



	public int getLogs_id() {
		return logs_id;
	}

	public void setLogs_id(int logs_id) {
		this.logs_id = logs_id;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public java.util.Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public long getApplication_time() {
		return application_duration;
	}

	public void setApplication_time(long application_time) {
		this.application_duration = application_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
