package com.hub4u.ams.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Tenant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	private LocalDate dob;
	
	private String address;
	private String phone;
	private String email;
	
	@Column(name = "WEB_SITE")
	private String webSite;
	
	@Column(name = "DT_CREATED")
	private LocalDateTime dateCreated;
	
	// Tenant who leaves will stay in the DB with 'active' = false 
	private boolean active; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tenant", fetch = FetchType.EAGER)
	private Set<TenantEmployee> employees = new HashSet<TenantEmployee>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "tenant", fetch = FetchType.EAGER)
	private List<ShopTenantRegistration> shops = new ArrayList<ShopTenantRegistration>();
	
	@Transient
	private String shopSelect;
	
	@Transient
	private String regTypeSelect;
	
	@Transient
	private int paidAmount;
	
	@Transient
	private String shopDescription;
	
	public Tenant() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Long getId() {
		return id;
	}

	public Set<TenantEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<TenantEmployee> employees) {
		this.employees = employees;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ShopTenantRegistration> getShops() {
		return shops;
	}

	public void setShops(List<ShopTenantRegistration> shops) {
		this.shops = shops;
	}

	public String getShopSelect() {
		return shopSelect;
	}

	public void setShopSelect(String shopSelect) {
		this.shopSelect = shopSelect;
	}	

	public String getRegTypeSelect() {
		return regTypeSelect;
	}

	public void setRegTypeSelect(String regTypeSelect) {
		this.regTypeSelect = regTypeSelect;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	@Override
	public String toString() {
		return "Tenant [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", webSite=" + webSite + ", dateCreated=" + dateCreated 
				+ ", active=" + active + "]";
	}
}
