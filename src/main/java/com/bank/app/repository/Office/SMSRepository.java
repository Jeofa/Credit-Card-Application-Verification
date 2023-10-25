package com.bank.app.repository.Office;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.entity.Office.SMS;

@Repository
public interface SMSRepository extends JpaRepository<SMS, Integer> {

	public SMS findSMSById(int id);
	
	

}
