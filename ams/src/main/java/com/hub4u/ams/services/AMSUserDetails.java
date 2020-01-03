package com.hub4u.ams.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hub4u.ams.model.UserAccount;

public class AMSUserDetails implements UserDetails {
	
	private UserAccount userAccount;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AMSUserDetails(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getUserAccount().getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
					.collect(Collectors.toSet());	
	}

	@Override
	public String getPassword() {
		return getUserAccount().getPassword();
	}

	@Override
	public String getUsername() {
		return getUserAccount().getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !getUserAccount().isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !getUserAccount().isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !getUserAccount().isCredentialsExpired();
	}

	@Override
	public boolean isEnabled() {
		return getUserAccount().isEnabled();
	}

}
