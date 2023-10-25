package com.bank.app.repository.Office;

import java.sql.Array;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bank.app.DTO.EmployersEmail;
import com.bank.app.DTO.LeadsDisplayData;
import com.bank.app.entity.Office.Leads;

public interface LeadsRepository extends JpaRepository<Leads, Integer>{

		@Query(nativeQuery = true, value = "SELECT CONCAT(per.`first_name`,' ',per.`middle_name`,' ',per.`last_name`)AS Name, per.person_id AS Pid, per.date_of_birth AS Dob, per.gender AS Gender, per.phone AS Phone, per.email AS Email, res.county AS County, res.box_number AS Box, emp.employment_type AS Employment, per.imagename AS Imagename, emp.salary AS Salary, bank.bank_name AS Bank, bank.bank_account AS BankNumber from personal_details per LEFT JOIN residential_details res ON per.residence = res.resident_id INNER JOIN employment_details emp ON per.employment = emp.emp_id INNER JOIN bank_details bank ON bank.bank_id = per.mybank WHERE per.id = ?1")
	List<ClientReview> getApplicant(int cid);

	@Query(nativeQuery = true, value = "SELECT CONCAT(pd.`first_name`,' ', pd.`middle_name`,' ', pd.`last_name`)AS Name, pd.`id` AS Id, pd.`person_id` AS Pid, emp.employment_type AS Employment, emp.email AS EmployersEmail, emp.salary AS Salary, leads.status AS Status FROM `personal_details` pd RIGHT JOIN employment_details emp ON pd.employment = emp.emp_id INNER JOIN leads ON leads.identity = pd.person_id;")
	List<PersonData> getPersonData();

	@Query(nativeQuery = true, value = "INSERT INTO `leads`( `email`, `employment`, `identity`, `location`, `name`, `phone`, `salary`) VALUES ('null','null','?','null','null','null','null','null')")
	void startSave(int person_Id);

	@Query(nativeQuery = true, value = "SELECT `lead_id`, `email`, `employment`, `identity`, `location`, `name`, `phone`, `salary` FROM `leads` WHERE `status` = ?")
	List<LeadsDisplayData> findByStatus(String status);
	
	@Query(nativeQuery = true, value = "SELECT  `phone` FROM `leads` WHERE `status` = ?")
	String[] findContactByStatus(String status);

	@Transactional 
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE `leads` SET `status`=? WHERE `identity` = ?")
	void updateById(String status, int lead_id);

	@Query(nativeQuery = true, value = "SELECT `email` FROM `clients`")
	List<EmployersEmail> getEmpEmail();

	
	
	
	


}
