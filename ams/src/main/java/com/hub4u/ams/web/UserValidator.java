package com.hub4u.ams.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hub4u.ams.model.UserAccount;
import com.hub4u.ams.services.UserAccountService;

@Component
public class UserValidator implements Validator {

	private UserAccountService userAccountService;
	
	public UserValidator(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAccount userAccount = (UserAccount)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
		
		if (userAccountService.getUserAccountByUserName(userAccount.getUserName()).isPresent()) {
			errors.rejectValue("userName", "UserForm.userName.duplicate");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		
		if (userAccount.getPassword().length() < 4) {
			errors.rejectValue("password", "UserForm.password.minsize");
		}
		
		if ( ! userAccount.getPassword().equals(userAccount.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "UserForm.confirmPassword.diff");
		}
		
	}

}
