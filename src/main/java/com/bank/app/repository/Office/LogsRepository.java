package com.bank.app.repository.Office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.entity.Office.Logs;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Integer>{
	

}
