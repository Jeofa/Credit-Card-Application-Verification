package com.bank.app.repository.Office;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.app.DTO.UsersData;
import com.bank.app.entity.Admin.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

	@Query(nativeQuery=true, 
			value="SELECT  `role_id` AS roleId,`role`,`role_desc` AS roleDesc FROM roles WHERE role_id NOT IN (SELECT role_id FROM user_roles WHERE user_id = ?)")
	Set<UsersData> findUsersNotRole(Integer userId);	

}
