package com.bank.app.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.entity.Users.EmploymentDetails;

public interface EmploymentRepo extends JpaRepository<EmploymentDetails, Integer> {

}
