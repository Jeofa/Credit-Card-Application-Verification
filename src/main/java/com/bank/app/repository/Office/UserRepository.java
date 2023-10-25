package com.bank.app.repository.Office;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.app.entity.Admin.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findUserByUsername(String username);

	@Query(nativeQuery=true,value="SELECT `password` FROM `users` WHERE `username` = ?")
	String getPasswordUser(String username);

	@Query(nativeQuery=true,value="SELECT COUNT(`user_id`) AS user FROM `users` WHERE `username` =?")
	String getUserByName(String username);
	

}
