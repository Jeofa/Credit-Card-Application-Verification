package com.bank.app.service.Office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bank.app.entity.Office.Logs;
import com.bank.app.repository.Office.LogsRepository;

@Service
public class LogsService {
	
	@Autowired
	private LogsRepository logsRepo;

	public String displayLogs(Logs logs, Model model) {
		model.addAttribute("logsData", logsRepo.findAll());
		return "Office/logs";
	}
	
	

}
