package com.bank.app.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.entity.Users.BankDetails;

public interface BankRepo extends JpaRepository<BankDetails, Integer> {

}
