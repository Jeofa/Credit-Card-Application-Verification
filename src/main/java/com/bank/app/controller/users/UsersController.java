package com.bank.app.controller.users;

import java.io.IOException;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bank.app.DTO.ErrorHandler;
import com.bank.app.entity.Users.BankDetails;
import com.bank.app.entity.Users.Card;
import com.bank.app.entity.Users.EmploymentDetails;
import com.bank.app.entity.Users.OtherDetails;
import com.bank.app.entity.Users.PersonalDetails;
import com.bank.app.entity.Users.ResidentialDetails;
import com.bank.app.service.Users.UserHandler;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserHandler userService;
	
	@GetMapping("/")
	private String getIndex(Card card, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("Session valid "+session.getId());
		session.invalidate();
		System.out.println("Session invalidated "+request.getSession().getAttribute("ID_SESSION"));
		return userService.cardsSelection(model,card);
	}
	
	@PostMapping("/card")
	public String postCard(Card card,Model model, HttpServletRequest request) {
		return userService.postCard( card,request);
	}
	
	@GetMapping("/personalDetails")
	private String getPersonForm(@RequestParam(value="cardid") int cardid,@RequestParam(value="error") String errorval, Model model, PersonalDetails person) {
		model.addAttribute("persons", person);
		ErrorHandler error = new ErrorHandler(errorval);
		model.addAttribute("error", error);
		return "User/personalDetails";
	}

	@PostMapping("/personalDetails")
	private String postPersonData(Model model, PersonalDetails person,@RequestParam(value="imagefile") MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException, ScriptException {
		return userService.postPersonData(person,model, file,request);
	}
	
	@GetMapping("/residentDetails")
	private String getResidentForm(@RequestParam(value="clientid") int clientid, Model model, ResidentialDetails resident) {
		model.addAttribute("resident", resident);
		return "User/residentialDetails";
	}
	
	
	@PostMapping("/residentDetails")
	private String postResidentData(Model model,  ResidentialDetails resident,HttpServletRequest request) {
		return userService.postResidentData(resident,model,request);
	}

	@GetMapping("/bankDetails")
	private String getBankForm(@RequestParam(value="error") String errorval,Model model, BankDetails bank) {
		ErrorHandler error = new ErrorHandler(errorval);
		model.addAttribute("error", error);
		model.addAttribute("bank", bank);
		return "User/bankDetails";
	}
	
	@PostMapping("/bankDetails")
	private String postBankData(Model model,MultipartFile file,   BankDetails bank, HttpServletRequest request) {
		return userService.postBankData(bank,file, model, request);
	}
	
	@GetMapping("/employmentDetails")
	private String getEmploymentForm(@RequestParam(value="error") String errorval,Model model, EmploymentDetails employ) {
		ErrorHandler error = new ErrorHandler(errorval);
		model.addAttribute("error", error);
		model.addAttribute("employ", employ);
		return "User/employmentDetails";
	}
	
	
	
	@PostMapping("/employmentDetails")
	private String postEmploymentData(Model model, MultipartFile file, EmploymentDetails employ,HttpServletRequest request) {
		return userService.postEmploymentData(employ,file,model,request);
	}

	@GetMapping("/otherDetails")
	private String getOtherForm(Model model, OtherDetails other) {
		model.addAttribute("other", other);
		return "User/otherDetails";
	}
	
	@PostMapping("/otherDetails")
	private String postOtherData(Model model,   OtherDetails other,HttpServletRequest request) {
		return userService.postOtherData(other,model,request);
	}
	
	@GetMapping("/approved")
	private String getApproved() {
//		((HttpServletRequest) session).getSession().invalidate();
		return "User/approved";
	}

}
