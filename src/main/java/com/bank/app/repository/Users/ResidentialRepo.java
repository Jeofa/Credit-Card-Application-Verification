package com.bank.app.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.entity.Users.ResidentialDetails;

public interface ResidentialRepo extends JpaRepository<ResidentialDetails, Integer> {

}
