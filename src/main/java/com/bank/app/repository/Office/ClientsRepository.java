package com.bank.app.repository.Office;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.ui.Model;

import com.bank.app.DTO.EmployersEmail;
import com.bank.app.DTO.GradClient;
import com.bank.app.entity.Office.Clients;
import com.bank.app.model.DashboardModel;

public interface ClientsRepository extends JpaRepository<Clients, Integer> {

	@Query(nativeQuery = true, value = "SELECT CONCAT(per.`first_name`,' ',per.`middle_name`,' ',per.`last_name`)AS Name, per.person_id AS Pid, per.date_of_birth AS Dob, per.gender AS Gender, per.phone AS Phone, per.email AS Email, res.county AS County, res.box_number AS Box, emp.employment_type AS Employment, per.imagename AS Imagename, emp.salary AS Salary, bank.bank_name AS Bank, bank.bank_account AS BankNumber from personal_details per LEFT JOIN residential_details res ON per.residence = res.resident_id INNER JOIN employment_details emp ON per.employment = emp.emp_id INNER JOIN bank_details bank ON bank.bank_id = per.mybank WHERE per.person_id = ?1")
	List<ClientReview> getApplicant(int cid);

	@Query(nativeQuery = true, value = "SELECT CONCAT(pd.`first_name`,' ', pd.`middle_name`,' ', pd.`last_name`)AS Name, pd.`id` AS Id, pd.`person_id` AS Pid, emp.employment_type AS Employment, emp.salary AS Salary FROM `personal_details` pd RIGHT JOIN employment_details emp ON pd.employment = emp.emp_id;")
	List<PersonData> getPersonData();
	
//	AND `identity` = ?
	@Query(nativeQuery = true, value="SELECT  `email`, `employment`, `identity`, `location`, `name`, `phone`, `salary`, `status` FROM `leads` WHERE  `email` = ? ")
	List<GradClient> graduateLeadToClient(String email);

	@Query(nativeQuery = true, value="SELECT `email` AS EmployersEmail FROM `clients` WHERE `identity` = ?")
	List<EmployersEmail> checkIfExists(int employeeId);

	@Query(nativeQuery = true,value="SELECT COUNT(`client_id`) AS clientNo FROM `clients`")
	String getNoOfClient();

	@Query(nativeQuery = true,value="SELECT COUNT(`lead_id`) FROM `leads` WHERE `status` = 'declined'")
	String rejectedApp();

	@Query(nativeQuery = true,value="SELECT COUNT(`client_id`) FROM clients WHERE `email` = ?")
	String ConfirmEmail(String email);
	
	@Query(nativeQuery = true, value ="DELETE FROM `clients` WHERE `identity` = ?")
	void deleteByIdentity(int id);
	
}
