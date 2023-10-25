package com.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.app.entity.Admin.Roles;
import com.bank.app.entity.Admin.Users;
import com.bank.app.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	/*
	 *  Login User...
	 */
	@GetMapping("/login")
	public String getUserLoginForm(@RequestParam String error,Model model, Users user) {
		if(error.isEmpty()) {
			model.addAttribute("user", user);
			return "Office/login";
		}else if(error == "badCredentials") {
			model.addAttribute("user", user);
			model.addAttribute("bCError", error);
			return "Office/login";
		}else {
			model.addAttribute("user", user);
			model.addAttribute("uNError", error);
			return "Office/login";
		}
	
//		return adminService.loadLoginForm(model,user);
	}
	
	@PostMapping("/logindata")
	public String loginData(Users user, Model model) {
		
		return adminService.loginData(model,user);
	}
	@GetMapping("/login_success_handler")
	public String loginSuccess() {
		return "redirect:/office/dashboard";
	}
	
	@GetMapping("/logout")
	public String loginOut(Users user, Model model) {
		return adminService.logOut(model,user);
	}
	
	
	/*
	 *  Register User...
	 */
	@GetMapping("/create/user")
	public String getUserCreationForm(Model model, Users user) {
		return adminService.userRegistrationForm(model,user);
	}
	
	@PostMapping("/create/user")
	public String postUserCreationData(Model model,Users user) {
		adminService.createUser(user);
		return "redirect:/office/users";
	}
	
	
	
	/*
	 *  Create Roles...
	 */
	
	@PostMapping("/create/role")
	public String postRoleData(Model model,Roles role) {
		return adminService.roleData(model,role);
	}
	
	@GetMapping("/delete/role")
	public String deleteRole(@RequestParam("id") int id, Model model) {
		adminService.deleteRole(id,model);
		return "redirect:/office/users";
	}
	
	@GetMapping("/update/role")
	public String updateRole(@RequestParam("id") int id,Model model) {
		adminService.updateRole(id, model);
		return "redirect:/office/users";
	}
	
	@GetMapping("/delete/user")
	public String deleteUser(@RequestParam("id") int id, Model model) {
		adminService.deleteUser(id,model);
		return "redirect:/office/users";
	}
	
	@GetMapping("/update/user")
	public String updateUser(@RequestParam("id") int id,Model model) {
		adminService.updateUser(id, model);
		return "redirect:/office/users";
	}
	
	
	
	/*
	 *  Manage user ...
	 */

	
	@GetMapping("/manage/user")
	public String manageUser(@RequestParam(value="user_id") int id, Model model) {
		adminService.manageUser(model,id);
		return "Office/user_roles";
	}
	
	@GetMapping("/manage/assign/role")
	public String assignRole(@RequestParam(value="role_id") int rid, @RequestParam(value="user_id") int uid,Model model) {
		boolean result = adminService.assignUserRole(uid, rid);
		System.out.println( "It's a success "+result);
		model.addAttribute("success", result == true);
		model.addAttribute("fail", result == false);
		return "redirect:/admin/manage/user?user_id="+uid;
	}
	
	@GetMapping("/manage/unassign/role")
	public String unassignRole(@RequestParam(value="role_id") int rid, @RequestParam(value="user_id") int uid) {
		boolean result = adminService.unassignUserRole(uid, rid);
		return "redirect:/admin/manage/user?user_id="+uid;
	}

}
