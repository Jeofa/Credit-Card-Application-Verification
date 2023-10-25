package com.bank.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Authentic {
	
	private String matchrate;
	private String responseID;
	private String quota;
	private String credit;
	
	public Authentic() {
		
	}

	public String getMatchrate() {
		return matchrate;
	}

	public void setMatchrate(String matchrate) {
		this.matchrate = matchrate;
	}

	public String getResponseID() {
		return responseID;
	}

	public void setResponseID(String responseID) {
		this.responseID = responseID;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	
	
	

}
