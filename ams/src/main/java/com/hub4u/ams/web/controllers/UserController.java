package com.hub4u.ams.web.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hub4u.ams.model.Role;
import com.hub4u.ams.model.UserAccount;
import com.hub4u.ams.services.RoleRepository;
import com.hub4u.ams.services.UserAccountService;
import com.hub4u.ams.web.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserValidator userValidator;

	public UserController() {
	}
	
	/**
	 * 
	 * */
	@GetMapping("/admin/users")
	public String getAllUserAccounts(Model model) {
		List<UserAccount> users = userAccountService.getAllUserAccounts();
		users.sort(Comparator.comparing(UserAccount::getDateCreated).reversed());
		
		model.addAttribute("users", users);
		return "admin/users";
	}
	
	// 
	private List<UserAccount> usersSortedByDateDesc() {
		List<UserAccount> users = userAccountService.getAllUserAccounts();
		users.sort(Comparator.comparing(UserAccount::getDateCreated).reversed());
		return users;
	}
	
	/**
	 * 
	 * */
	@PostMapping("/admin/users")
	public String createNewUserAccount(@ModelAttribute UserAccount userAccountForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
		
//		userValidator.validate(userAccountForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			log.error(">>> createNewUserAccount() - Failed UserAccount Validation ! ");
			model.addAttribute("create_error", true);
			return "admin/usercreateform";
		}
		
		userAccountForm.setDateCreated(LocalDateTime.now());
		userAccountForm.setPassword(bCryptPasswordEncoder.encode(userAccountForm.getPassword()));
		
		String[] roles = request.getParameterValues("role");
//		for (String role : roles) {
//			Role rol = roleRepository.findByRoleName(role);
//			userAccountForm.getRoles().add(rol);
//		}
		
		
		
		Arrays.stream(roles).forEach(roleName -> {
			Role role = roleRepository.findByRoleName(roleName);
			userAccountForm.getRoles().add(role);
		});
		
		UserAccount user = userAccountService.createUserAccount(userAccountForm);
		log.info("createNewUserAccount() - Created new UserAccount :  \n" + user);
		
		model.addAttribute("username", user.getUserName());
		model.addAttribute("users", usersSortedByDateDesc() );
		model.addAttribute("create_success", true);
		
		return "admin/users";
	}
	
	/**
	 * @DeleteMapping("admin/users/{id}")
	 * */
	@GetMapping("/admin/userdel/{id}") 
	public String deleteUserAccount(@PathVariable Long id, Model model) {
		userAccountService.deleteUserAccountById(id);
		log.info("deleteUserAccount() - Deleted UserAccount with ID : " + id);

		List<UserAccount> users = userAccountService.getAllUserAccounts();
		users.sort(Comparator.comparing(UserAccount::getDateCreated).reversed());
		
		model.addAttribute("userId", id);
		model.addAttribute("users", users);
		model.addAttribute("delete_success", true);

		return "admin/users";
	}
	
	
	/**
	 * 
	 * */
	@GetMapping("/admin/usercreateform")
	public String createUserAccountForm(Model model) {
		UserAccount userAccount = new UserAccount();
		userAccount.setEnabled(true);
		model.addAttribute("userAccount", userAccount); 
		return "admin/usercreateform";
	}	

}
