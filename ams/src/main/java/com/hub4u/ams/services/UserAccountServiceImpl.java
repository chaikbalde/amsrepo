package com.hub4u.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hub4u.ams.frwk.UserNotFoundException;
import com.hub4u.ams.model.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	private UserAccountRepository repository;

	public UserAccountServiceImpl(UserAccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserAccount createUserAccount(UserAccount user) {
		return repository.save(user);
	}

	@Override
	public UserAccount getUserAccount(Long id) throws UserNotFoundException {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Unable to find UserAccount with ID : " + id));
	}

	@Override
	public Optional<UserAccount> getUserAccountByUserName(String userName) throws UserNotFoundException {
		return repository.findByUserName(userName);
	}

	@Override
	public List<UserAccount> getAllUserAccounts() {
		return repository.findAll();
	}

	@Override
	public UserAccount updateUserAccount(Long id, UserAccount newuser) {
		return repository.findById(id)
				.map(user -> {
					user.setFirstName(newuser.getFirstName());
					user.setLastName(newuser.getLastName());
					user.setUserName(newuser.getUserName());
					user.setPassword(newuser.getPassword());
					user.setEnabled(newuser.isEnabled());
					user.setAccountExpired(newuser.isAccountExpired());
					user.setCredentialsExpired(newuser.isCredentialsExpired());
					user.setLocked(newuser.isLocked());
					
					return repository.save(user);
				} )
				.orElseThrow(() -> new UserNotFoundException("Failed to update UserAccount. Not found User with ID: " + id));
	}

	@Override
	public void deleteUserAccount(UserAccount user) {
		repository.delete(user);
	}
	
	@Override
	public void deleteUserAccountById(Long id) {
		repository.deleteById(id);
	}
}
