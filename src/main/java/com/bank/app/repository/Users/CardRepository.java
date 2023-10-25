package com.bank.app.repository.Users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.app.entity.Users.Card;
import com.bank.app.service.Office.CardSort;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{

	@Query(nativeQuery=true,value="SELECT * FROM `card` WHERE `card_id` = ?")
	Card getCard(int card);
	
	

	@Query(nativeQuery=true,value="SELECT COUNT(pd.id) AS noOfCards ,cd.card_name AS cardName FROM personal_details pd INNER JOIN card cd WHERE cd.card_id = pd.card GROUP BY pd.card;")
	List<CardSort> getCardsData();

}
