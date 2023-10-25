package com.bank.app.service.Users;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bank.app.DTO.MailRequest;
import com.bank.app.entity.OCR;
import com.bank.app.entity.Office.Leads;
import com.bank.app.entity.Office.Logs;
import com.bank.app.entity.Users.BankDetails;
import com.bank.app.entity.Users.Card;
import com.bank.app.entity.Users.EmploymentDetails;
import com.bank.app.entity.Users.OtherDetails;
import com.bank.app.entity.Users.PersonalDetails;
import com.bank.app.entity.Users.ResidentialDetails;
import com.bank.app.model.Authentic;
import com.bank.app.repository.Office.LeadsRepository;
import com.bank.app.repository.Office.LogsRepository;
import com.bank.app.repository.Users.BankRepo;
import com.bank.app.repository.Users.CardRepository;
import com.bank.app.repository.Users.EmploymentRepo;
import com.bank.app.repository.Users.OtherDetailsRepo;
import com.bank.app.repository.Users.PersonalRepo;
import com.bank.app.repository.Users.ResidentialRepo;
import com.bank.app.service.Email.EmailService;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class UserHandler {
	
	private int viewid;
	
	public String error_message;
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private PersonalRepo personRepo;
	
	@Autowired
	private EmploymentRepo empRepo;
	
	@Autowired
	private ResidentialRepo resRepo;
	
	@Autowired
	private BankRepo bankRepo;
	
	@Autowired
	private OtherDetailsRepo otherRepo;
	
	@Autowired
	private LeadsRepository leadsrepo;
	
	@Autowired
	private LogsRepository logsRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EmailServiceImpl emailImp;
	
	@Autowired
	private EmailService service;
	
	private Instant starttime;
	
	public String postCard( Card card, HttpServletRequest request) {
		
		request.getSession().setAttribute("CARD_SESSION", card.getCardId());
		return "redirect:/users/personalDetails?cardid=" + card.getCardId()+"&error="+null;
	}

	public String postPersonData(PersonalDetails person, Model model,MultipartFile file, HttpServletRequest request) throws ScriptException {
		starttime = Instant.now();
		  request.getSession().setAttribute("STARTTIME_SESSION", starttime);
        @SuppressWarnings("unchecked")
		Object id_data =  request.getSession().getAttribute("ID_SESSION");
        if (id_data == null) {
            request.getSession().setAttribute("ID_SESSION", person.getPerson_Id());
            request.getSession().setAttribute("EMAIL_SESSION", person.getEmail());
            request.getSession().setAttribute("TEL_SESSION", person.getPhone());
            request.getSession().setAttribute("NAME_SESSION", person.getFname()+ " "+person.getLname());
        }
        request.getSession().setAttribute("ID_SESSION", person.getPerson_Id());
        request.getSession().setAttribute("EMAIL_SESSION", person.getEmail());
        request.getSession().setAttribute("TEL_SESSION", person.getPhone());
        request.getSession().setAttribute("NAME_SESSION", person.getFname()+ " "+person.getLname());
        System.out.println("--------------------------session------------------------");
        System.out.println("ID: "+request.getSession().getAttribute("ID_SESSION"));
        System.out.println("EMAIL: "+request.getSession().getAttribute("EMAIL_SESSION"));
        System.out.println("TEL: "+request.getSession().getAttribute("TEL_SESSION"));
        
		viewid = person.getPerson_Id();
		int clientid = person.getPerson_Id();
		
		int cardid =(int) request.getSession().getAttribute("CARD_SESSION");
		Card carder = cardRepo.getCard(cardid);
		
		person.setMycard(carder);
		person.setId_img("image_"+person.getPerson_Id()+"_"+file.getOriginalFilename());
	
		try {
			String image_path = "/home/CCVAIMG/"+person.getId_img();
			file.transferTo(new File(image_path));
			OCR ocr = new OCR();
//			boolean id_result = ocr.checkIDData(person.getId_img(), viewid);
			boolean id_result =true;
			if(id_result) {
				personRepo.save(person);
				return "redirect:/users/residentDetails?clientid="+clientid;
			}else {
				error_message = "error";
				String card =  String.valueOf(request.getSession().getAttribute("CARD_SESSION"));
				return "redirect:/users/personalDetails?cardid=" + card+"&error="+error_message;
			}
//			
			}catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				error_message = "Try Again";
				String card = String.valueOf(request.getSession().getAttribute("CARD_SESSION"));
				return "redirect:/users/personalDetails?cardid=" + card+"&error="+error_message;
			}
		
	}

	public String postResidentData(ResidentialDetails resident, Model model, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
		List<String> location_data = (List<String>) request.getSession().getAttribute("LOCATION_SESSION");
        //check if id_data is present in session or not
        if (location_data == null) {
        	location_data = new ArrayList<>();
            // if id_data object is not present in session, set id_session in the request session
            request.getSession().setAttribute("LOCATION_SESSION", resident.getLocation());
        }
        
		
		resRepo.save(resident);
		personRepo.saveResidence(resident.getResident_id(),viewid);
		String default_error = null;
		return "redirect:/users/employmentDetails?error="+default_error;
	}
	
	public String postEmploymentData(EmploymentDetails employ, MultipartFile file, Model model,HttpServletRequest request) {

		String filename = file.getOriginalFilename();
		String image_path = "/home/CCAVPaySlip/"+viewid+"_"+filename;
		employ.setPayslip(viewid+"_"+filename);
		String name = (String)request.getSession().getAttribute("NAME_SESSION");
		try {
			file.transferTo(new File(image_path));
			OCR ocr = new OCR();
			ocr.checkPaySlipData(filename, name);
			boolean payslip_result =true;
			if(payslip_result) {
				  @SuppressWarnings("unchecked")
					List<String> emp_data = (List<String>) request.getSession().getAttribute("EMPT_SESSION");
			        if (emp_data == null) {
			        	emp_data = new ArrayList<>();
			            // if id_data object is not present in session, set id_session in the request session
			            request.getSession().setAttribute("EMPT_SESSION", employ.getEmpType());
			            request.getSession().setAttribute("EMPSal_SESSION", employ.getSalary());
			            request.getSession().setAttribute("EMPEMAIL_SESSION", employ.getEmail());
			        }
			        request.getSession().setAttribute("EMPT_SESSION",employ.getEmpType());
			        request.getSession().setAttribute("EMPSal_SESSION", employ.getSalary());
			        request.getSession().setAttribute("EMPEMAIL_SESSION", employ.getEmail());
			        System.out.println("--------------------------session------------------------");
			        System.out.println("EMP: "+request.getSession().getAttribute("EMPT_SESSION"));
			        System.out.println("EMPSal: "+request.getSession().getAttribute("EMPSal_SESSION"));
				
				empRepo.save(employ);
				personRepo.saveEmployment(employ.getEmp_id(),viewid);
				String default_error = null;
				return "redirect:/users/bankDetails?error="+default_error;
			}else {
				error_message = "error";
				return "redirect:/users/employmentDetails?error="+error_message;
			}
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			error_message = "error";
			return "redirect:/users/employmentDetails?error="+error_message;
		}
		
		
	}
	
	public String postBankData(BankDetails bank, MultipartFile file, Model model,HttpServletRequest request) {
		
		String filename = file.getOriginalFilename();
		String image_path = "/home/CCAVBankStmt/"+viewid+"_"+filename;
		bank.setBankStmt(viewid+"_"+file.getOriginalFilename());
		String name = (String)request.getSession().getAttribute("NAME_SESSION");
		try {
			file.transferTo(new File(image_path));
			OCR ocr = new OCR();
//			 ocr.checkBankData(bank.getBankStmt(), name);
			boolean bank_result = true;
			if(bank_result) {
				bankRepo.save(bank);
				personRepo.saveBank(bank.getBank_id(),viewid);
				return "redirect:/users/otherDetails";
			}else {
				error_message = "error";
				return "redirect:/users/bankDetails?error="+error_message;
			}
		}catch (Exception e) {
			error_message = "error";
			return "redirect:/users/bankDetails?error="+error_message;
		}
	
		
	}

	public String postOtherData(OtherDetails other, Model model,HttpServletRequest request) {
		System.out.println("Get Other Data Ready");
		String employment = (String) request.getSession().getAttribute("EMPT_SESSION");
		String email = (String) request.getSession().getAttribute("EMAIL_SESSION");
		String tel = (String) request.getSession().getAttribute("TEL_SESSION");
		double salary = (double) request.getSession().getAttribute("EMPSal_SESSION");
		String location = (String) request.getSession().getAttribute("LOCATION_SESSION");
		String name = (String)request.getSession().getAttribute("NAME_SESSION");
		String status = "pending";
		int id = (int) request.getSession().getAttribute("ID_SESSION");
		
		Leads lead = new Leads(name,email,tel,salary,id,employment,location, status);
		
		
		Date creation = new Date();
		Instant endtime = Instant.now();
		
		Temporal Starttime = (Temporal) request.getSession().getAttribute("STARTTIME_SESSION");
		Duration timeElapsed = Duration.between(Starttime, endtime);
		Logs log = new Logs(id,creation,timeElapsed.toMinutes(),"created");
		
		logsRepo.save(log);
		leadsrepo.save(lead);
		otherRepo.save(other);
//		personRepo.saveOther(other.getId(),viewid);

//		now send email and sms to : employer and owner.
//		String empid = request.getSession().getAttribute("ID_SESSION");
		
		MailRequest data = new MailRequest();
		data.setEmpid(id);
		data.setEmpname(name);
		data.setTo(email);
		data.setFrom("bnk.bank.ke@gmail.com");
		data.setSubject("Credit Card Employee Confirmation");
		Map<String, Object> map = new HashMap<>();
		map.put("Name", data.getEmpname());
		map.put("Id", data.getEmpid());
		service.sendEmail(data, map);
		
		return "redirect:/users/approved";
	}

	public String cardsSelection( Model model, Card card) {
		model.addAttribute("card", card);
		return "User/index";
	}



	
}
