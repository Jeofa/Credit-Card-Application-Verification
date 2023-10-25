package com.bank.app.DTO;

import java.util.Map;

public interface Dashboard {
	
	public int getClientNo();
	public int getRejectedApps();
	public int getApplicationRate();
	public Map<String,String> getCards();

}
