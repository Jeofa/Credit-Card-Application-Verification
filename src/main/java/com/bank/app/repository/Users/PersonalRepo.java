package com.bank.app.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bank.app.entity.Users.PersonalDetails;

public interface PersonalRepo extends JpaRepository<PersonalDetails, Integer> {
	@Transactional 
	@Modifying
	@Query(nativeQuery = true, value="UPDATE `personal_details` SET `residence` = ? WHERE `person_id` = ?")
	void saveResidence(int i,int viewid);

	@Transactional 
	@Modifying
	@Query(nativeQuery = true, value="UPDATE `personal_details` SET `employment` = ? WHERE `person_id` = ?")
	void saveEmployment(int emp_id, int viewid);

	@Transactional 
	@Modifying
	@Query(nativeQuery = true, value="UPDATE `personal_details` SET `details` = ? WHERE `person_id` = ?")
	void saveOther(int id, int viewid);

	@Transactional 
	@Modifying
	@Query(nativeQuery = true, value="UPDATE `personal_details` SET `mybank` = ? WHERE `person_id` = ?")
	void saveBank(int bank_id, int viewid);

	


}
