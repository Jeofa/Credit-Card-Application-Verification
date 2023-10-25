package com.bank.app.model;

import java.util.List;
import java.util.Map;

import com.bank.app.entity.Users.Card;

public class DashboardModel {
	
	private int clientNo;
	private float applicationRate;
	private int rejectedApps;
//	private List<Card> cards; 
	
	public DashboardModel() {
		
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public float getApplicationRate() {
		return applicationRate;
	}

	public void setApplicationRate(float applicationRate) {
		this.applicationRate = applicationRate;
	}

	public int getRejectedApps() {
		return rejectedApps;
	}

	public void setRejectedApps(int rejectedApps) {
		this.rejectedApps = rejectedApps;
	}

//	public List<Card> getCards() {
//		return cards;
//	}
//
//	public void setCards(List<Card> list) {
//		this.cards = list;
//	}

	
}
