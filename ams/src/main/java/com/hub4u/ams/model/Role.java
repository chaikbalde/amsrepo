package com.hub4u.ams.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "ROLE_")
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	private String roleName;
	
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
	private Set<UserAccount> userAccounts;

	public Role() {
		userAccounts = new HashSet<UserAccount>();
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
		userAccounts = new HashSet<UserAccount>();
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
	
	

}
