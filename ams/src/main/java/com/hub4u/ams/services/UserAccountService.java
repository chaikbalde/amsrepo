package com.hub4u.ams.services;

import java.util.List;
import java.util.Optional;

import com.hub4u.ams.frwk.UserNotFoundException;
import com.hub4u.ams.model.UserAccount;

public interface UserAccountService {
	
	UserAccount createUserAccount(UserAccount userAccount); 
	UserAccount getUserAccount(Long id) throws UserNotFoundException;
//	UserAccount getUserAccountByUserName(String userName) throws UserNotFoundException;
	Optional<UserAccount>  getUserAccountByUserName(String userName);
	List<UserAccount> getAllUserAccounts();
	UserAccount updateUserAccount(Long id, UserAccount newUserAccount);
	void deleteUserAccount(UserAccount userAccount);
	void deleteUserAccountById(Long id);

}
