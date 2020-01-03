package com.hub4u.ams.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hub4u.ams.frwk.UserNotFoundException;
import com.hub4u.ams.model.UserAccount;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserAccountService userAccountService;

	public UserDetailsServiceImpl(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAccount userAccount = userAccountService.getUserAccountByUserName(username)
					.orElseThrow(() -> new UserNotFoundException("Unable to find UserAccount with userName : " + username));
		
		AMSUserDetails userDetails = new AMSUserDetails(userAccount);
		
//		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//		userAccount.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
//		return new User(username, userAccount.getPassword(), userAccount.isEnabled(), ! userAccount.isAccountExpired(), ! userAccount.isCredentialsExpired(), ! userAccount.isLocked(), authorities);
		
		return userDetails;
	}

}
