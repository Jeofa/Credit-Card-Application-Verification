package com.bank.app.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bank.app.DTO.UsersData;
import com.bank.app.Senders.EmailUtilImpl;
import com.bank.app.entity.Admin.PasswordGen;
import com.bank.app.entity.Admin.Roles;
import com.bank.app.entity.Admin.Users;
import com.bank.app.model.UserModel;
import com.bank.app.repository.Office.RoleRepository;
import com.bank.app.repository.Office.UserRepository;

@Service
public class AdminService  implements UserDetailsService{
	
	
	private boolean isAuthenticated;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JdbcUserDetailsManager userManager;
	
	@Autowired
	private EmailUtilImpl emailUtil;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var currentUser = userRepo.findUserByUsername(username);
		Users user = currentUser.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		return new UserModel(user);
	}
	
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	
	public String loadLoginForm(Model model, Users user) {
		model.addAttribute("",user);
		return "Office/login";
	}


	/*
	 *  Register User...
	 */
	public String userRegistrationForm(Model model, Users user) {
		model.addAttribute("userinfo", user);
		
		return "redirect:/office/users";
	}
	

	
	public void createUser(Users user) {
		
		PasswordGen pwd = new PasswordGen();
		String username = user.getUsername();
		String email = user.getEmail();
		String password = pwd.passwordGenerator().toString();
		System.out.println("This is your password...===>");
		System.out.println(password);
		user.setPassword(passwordEncoder.encode(password));
		
		userRepo.save(user);
//		userManager.createUser(new UserModel(user));
		
		String body = "You Have Received this email for authentication. If you are unaware of it, kindly ingore.\n"
				+ "Your information includes:\n Usename: "+username+"\n Password: "+password+" "
						+ "\n\nThank you.";
		
		//we send the email to user using their details
		emailUtil.sendEmail(email, "BNK Bank Login Details", body);
	}

	public String userRegistrationData(Model model, Users user) {
		userRepo.save(user);
		return "redirect:/login";
	}

	public void userForm(Model model, Users users) {
		model.addAttribute("userinfo", users);
		model.addAttribute("usersData", userRepo.findAll());
	}

	/*
	 *  Role Creation...
	 */
	public String roleForm(Model model, Roles role) {
		model.addAttribute("roleinfo", role);
		model.addAttribute("roleData", roleRepo.findAll());
		return "roles";
	}


	public String roleData(Model model,  Roles role) {
		roleRepo.save(role);
		return "redirect:/office/users";
	}
	public void manageUser(Model model,int id) {
		model.addAttribute("manageUser", userRepo.getReferenceById(id));
		model.addAttribute("Roles", getUserRoles(userRepo.getReferenceById(id)));
		model.addAttribute("NotRoles", getUserNotRoles(userRepo.getReferenceById(id)));
	}

	
	
	public boolean assignUserRole(Integer userId, Integer roleId){
//		Find the users and the roles (id)
		boolean success;
		try {
			success = true;
			
			Users user = userRepo.findById(userId).orElse(null);
			Roles role = roleRepo.findById(roleId).orElse(null);
			System.out.println("Here is the success11:: "+success);
			Set<Roles> userRoles = user.getUserRoles();
			userRoles.add(role);
			user.setUserRoles(userRoles);
			userRepo.save(user);
			
			return success;
		}catch (Exception e) {
			success = false;
			System.out.println("Here is the success22:: "+success);
			return success;
		}
	}
	
	
	public boolean unassignUserRole(Integer userId, Integer roleId) {
		
		boolean success;
		try {
			success = true;
			System.out.println("Here is the success11:: "+success);
			Users user = userRepo.findById(userId).orElse(null);
			Set<Roles> userRoles = user.getUserRoles();
			
			userRoles.removeIf(x->x.getRole_id() == roleId );
			
			userRepo.save(user);
			
			return success;
		}catch (Exception e) {
			success = false;
			return success;
		}
	}
	
	public Set<Roles> getUserRoles(Users user){
		return user.getUserRoles();
	}
	
	//roles that users don't have
	public Set<UsersData> getUserNotRoles(Users user){
		return roleRepo.findUsersNotRole(user.getUser_id());
	}
	public void deleteRole(int id, Model model) {
		roleRepo.deleteById(id);
	}
	public void updateRole(int id, Model model) {
		
	}
	public void deleteUser(int id, Model model) {
		
	}
	public void updateUser(int id, Model model) {
		
	}
	public String loginData(Model model, Users user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String userexists = userRepo.getUserByName(username);
		
		switch(userexists) {
		case "1":
			System.out.println("Case 1:: User: "+username+" Exists: "+userexists);
			String encodedPassword = userRepo.getPasswordUser(username);
			System.out.println("Encoded "+encodedPassword);
			if(passwordEncoder.matches(password, encodedPassword)) {
				setAuthenticated(true);
				return "redirect:/admin/login_success_handler";
			}else {
				setAuthenticated(false);
				String error = "badCredentials";
				return "redirect:/admin/login?error="+error;
			}
		case "0":
			System.out.println("Case 0:: User: "+username+" Exists: "+userexists);
			String error = "userNotFound";
			return "redirect:/admin/login?error="+error;
		default :
			String errord = "Error";
			return "redirect:/admin/login?error="+errord;
		}
	}


	public String logOut(Model model, Users user) {
		setAuthenticated(false);
		return "redirect:/admin/login?error=null";
	}
	
	
	
	
	



	
	
	

}
