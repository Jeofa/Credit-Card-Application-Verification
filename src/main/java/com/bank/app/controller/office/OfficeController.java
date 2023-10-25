package com.bank.app.controller.office;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.app.entity.Admin.Roles;
import com.bank.app.entity.Admin.Users;
import com.bank.app.entity.Office.Clients;
import com.bank.app.entity.Office.Leads;
import com.bank.app.entity.Office.Logs;
import com.bank.app.entity.Office.SMS;
import com.bank.app.model.BulkSms;
import com.bank.app.model.DashboardModel;
import com.bank.app.model.LeadsUpdate;
import com.bank.app.service.AdminService;
import com.bank.app.service.Office.ClientService;
import com.bank.app.service.Office.DashboardService;
import com.bank.app.service.Office.LeadsService;
import com.bank.app.service.Office.LogsService;
import com.bank.app.service.Office.SmsService;

@Controller
@RequestMapping("/office")
public class OfficeController {
	
	@Autowired 
	private AdminService adminService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private LeadsService leadsService;
	
	@Autowired
	private LogsService logsService;
	
	@Autowired
	private DashboardService dashService;
	
	@GetMapping("/")
	public String getLogin() {
		return "Office/login";
	}
	@GetMapping("/profile")
	public String getProfile() {
		return "Office/profile";
	}
	@GetMapping("/logout")
	public String logout() {
		return "Office/profile";
	}
	@GetMapping("/dashboard")
	public String getDashboard(Model model, DashboardModel dashboard) {
//		if(adminService.isAuthenticated()) {
			return dashService.getDashboardDisplay(model,dashboard);
//		}else {
//			return "redirect:/admin/login?error=null";
//		}
		
	}
	
	
	/*
	 * Clients
	 */
	@GetMapping("/clients")
	public String getClients(Clients clients, Model model) {
			return clientService.getAllClient(clients, model);
	}
	@GetMapping("/clientsreview")
	public String getClientsReview(@RequestParam("id") int cid, Clients clients, Model model) {
		return clientService.getFullDetails(cid,clients,model);
	}
	@GetMapping("/updateclient")
	public String updateclient(@RequestParam("id") int id,Model model) {
		clientService.updateClient(id,model);
		return "Office/clients";
	}
	@GetMapping("/deleteclient")
	public String deleteclient(@RequestParam("id") int id,Model model) {
		clientService.deleteClient(id,model);
		return "redirect:/office/clients";
	}
	
	
//	@GetMapping("/leads")
//	public String getLeads(Leads leads, Model model) {
////		model.addAttribute("leads", leads);
//		return "Office/leads";
//	}
	@GetMapping("/leads")
	public String getLeads(Leads leads, Model model) {
			return leadsService.getAllLeads(leads, model);
	}
	@GetMapping("/leadsreview")
	public String getLeadsReview(@RequestParam("id") int cid, Leads leads, Model model) {
		return leadsService.getFullDetails(cid,leads,model);
	}
	@PostMapping("/updateleads")
	public String updateLeads(LeadsUpdate leads,Model model) {
		leadsService.updateLeads(leads,model);
		return "redirect:/office/leads";
	}
	@GetMapping("/deleteleads")
	public String deleteLeads(@RequestParam("id") int id,Model model) {
		leadsService.deleteClient(id,model);
		return "redirect:/office/leads";
	}
	@GetMapping("/offcan")
	public String dasa(Model model) {
		return "Office/offcan";
	}
	

	
	/**
	 * SMS block
	 */
	@GetMapping("/manage/sms")
	public String getManageSMS(SMS sms, Model model) {
		smsService.getAllSmsToDispatch(sms,model);
		return "Office/sms-dispatch";
	}
	
	@GetMapping("/sms")
	public String getSMS(SMS sms, Model model) {
		return smsService.getSmsForm(model,sms);
	}
	
	@PostMapping("/smspost")
	public String postSMS(SMS sms, Model model) {
		return smsService.saveSmsForm(sms,model);
//		return "Office/sms";
	}
	
	@GetMapping("/updatesms")
	public String updateSMS(@RequestParam("id") int id,Model model) {
		smsService.updateSms(id,model);
		return "Office/sms";
	}
	
	@GetMapping("/deletesms")
	public String deleteSMS(@RequestParam("id") int id,Model model) {
		smsService.deleteSms(id,model);
		return "redirect:/office/sms";
	}
	
	
//	manage sms
	@GetMapping("/ajaxloads")
	public String loadData(@RequestParam("id") int  id,Model model) {
		return smsService.loadToSendSms(id,model);
	}
	
	@PostMapping("/sendbulk")
	public String sendBulkSms(BulkSms bulk, Model model) {
		return smsService.sendBulkSms(bulk,model);
	}
	

	@GetMapping("/users")
	public String getUsers(Users users, Roles role, Model model) {
		adminService.roleForm(model,role);
		adminService.userForm(model,users);
		return "Office/users2";
	}
	
	@GetMapping("/logs")
	public String getLogs(Logs logs, Model model) {
		return logsService.displayLogs(logs,model);
//		return "Office/logs";
	}

}
