package com.bank.app.entity.Users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Card {
	
	@Id
	private int cardId;
	
	private String cardName;
	

	@OneToOne(mappedBy="mycard")
	private PersonalDetails my_card;
	

	public Card() {
		
	}
	
	

	public Card(int cardId, String cardName) {
		this.cardId = cardId;
		this.cardName = cardName;
	}



	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public PersonalDetails getPerson() {
		return  my_card;
	}

	public void setPerson(PersonalDetails person) {
		this. my_card = person;
	}
	

}
