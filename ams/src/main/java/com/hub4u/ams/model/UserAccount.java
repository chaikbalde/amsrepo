package com.hub4u.ams.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings(value = "all")
@Getter @Setter
@Entity
@Table(name = "USERACCOUNT")
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Please enter your first name")
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	@Transient
	private String confirmPassword;
	private boolean enabled;
	private boolean accountExpired;
	private boolean credentialsExpired;
	private boolean locked;
	private LocalDateTime dateCreated;
	
	/**
	 *  Relationship is configured @ Owner side
	 * */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "USER_ROLE", 
		joinColumns = @JoinColumn(name = "useraccount_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id") )
	private Set<Role> roles; 
	
	public UserAccount() {
		roles = new HashSet<Role>();
	}
	
	public UserAccount(String firstName, String lastName, String userName, String password, boolean enabled, LocalDateTime dateCreated) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;  
		this.enabled = enabled;
		this.dateCreated = dateCreated;
		roles = new HashSet<Role>();
	}

	@Override
	public String toString() {
		return "UserAccount [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", enabled=" + enabled
				+ ", accountExpired=" + accountExpired + ", credentialsExpired=" + credentialsExpired + ", locked="
				+ locked + ", dateCreated=" + dateCreated + "]";
	}
	
}
