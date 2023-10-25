package com.bank.app.service.Office;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bank.app.entity.Office.SMS;
import com.bank.app.model.Africastalking;
import com.bank.app.model.BulkSms;
import com.bank.app.repository.Office.LeadsRepository;
import com.bank.app.repository.Office.SMSRepository;

@Service
public class SmsService {
	
	@Autowired
	private SMSRepository smsRepo;
	
	@Autowired
	private LeadsRepository leadsRepo;

	@Autowired 
	private  Africastalking africastalking;
	
	public String getSmsForm(Model model, SMS sms) {
		model.addAttribute("smsinfo", sms);
		model.addAttribute("smsdata", smsRepo.findAll());
		return "Office/sms";
	}
	
	public String saveSmsForm(SMS sms,Model model) {
		smsRepo.save(sms);
		return "redirect:/office/sms";
	}

	public void updateSms(int id, Model model) {
		SMS sms = smsRepo.findSMSById(id);
		model.addAttribute("smsinfo", sms);
	}

	public void deleteSms(int id, Model model) {
		smsRepo.deleteById(id);
	}

	public void getAllSmsToDispatch(SMS sms, Model model) {
		BulkSms bulk = new BulkSms();
		model.addAttribute("mysms", smsRepo.findAll());
		model.addAttribute("upforbulk", bulk);
	}

	public String loadToSendSms(int id, Model model) {
		String status ;
		switch(id) {
		case 1:
			status = "approved";
			model.addAttribute("myajaxload",  leadsRepo.findByStatus(status));
			break;
		case 2:
			status = "pending";
			model.addAttribute("myajaxload",  leadsRepo.findByStatus(status));
			break;
		case 3:
			status = "declined";
			model.addAttribute("myajaxload",  leadsRepo.findByStatus(status));
			break;
			
		
		}
		
		return "Office/display";
	}

	public String sendBulkSms(BulkSms bulk, Model model) {
//		get the contacts based on the to-id
		String status ;
		String[] contacts;
		SMS message;
		switch(bulk.getTo()) {
		case 1:
			status = "approved";
			contacts = (String[]) leadsRepo.findContactByStatus(status);
			message = smsRepo.findSMSById(bulk.getWhat());
			africastalking.sendBulkSms(contacts, message.getHeading()+" "+ message.getMessage());
			break;
		case 2:
			status = "pending";
			System.out.println(leadsRepo.findContactByStatus(status));
			contacts = (String[]) leadsRepo.findContactByStatus(status);
			message = smsRepo.findSMSById(bulk.getWhat());
			africastalking.sendBulkSms(contacts, message.getHeading()+" "+ message.getMessage());
			break;
		case 3:
			status = "declined";
			contacts = (String[]) leadsRepo.findContactByStatus(status);
			message = smsRepo.findSMSById(bulk.getWhat());
			africastalking.sendBulkSms(contacts, message.getHeading()+" "+ message.getMessage());
			break;
			
		
		}
		return "redirect:/office/sms";
	}
	

}
