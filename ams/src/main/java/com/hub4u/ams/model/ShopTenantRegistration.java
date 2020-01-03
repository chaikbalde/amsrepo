package com.hub4u.ams.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

@Entity
public class ShopTenantRegistration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ShopTenantRegistrationId id;
	
	@ManyToOne
	@MapsId("shop_id")
	@JoinColumn(name = "shop_id")
	private Shop shop;
	
	@ManyToOne
	@MapsId("tenant_id")
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	@Column(name = "DATE_START_REG")
	private LocalDate dateStartRegistration;
	
	@Column(name = "DATE_END_REG")
	private LocalDate dateEndRegistration;
	
	@Column(name = "SHOP_TENANT_DESCRIPTION")
	private String shopTenantDescription;
	
	private RegistrationType registrationType; 
	
	private int expectedAmount;
	
	/**
	 * Amount paid for the current rent period (MONTH, QUARTER, ...)
	 * */ 
	private int currentPaidAmount;
	
	private LocalDate nextPaymentDate;

	@OneToMany(mappedBy = "shopTenantRegistration", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Payment> payments;
	
	
	public ShopTenantRegistration() {
		payments = new ArrayList<Payment>();
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public LocalDate getDateStartRegistration() {
		return dateStartRegistration;
	}

	public void setDateStartRegistration(LocalDate dateStartRegistration) {
		this.dateStartRegistration = dateStartRegistration;
	}

	public LocalDate getDateEndRegistration() {
		return dateEndRegistration;
	}

	public void setDateEndRegistration(LocalDate dateEndRegistration) {
		this.dateEndRegistration = dateEndRegistration;
	}

	public String getShopTenantDescription() {
		return shopTenantDescription;
	}

	public void setShopTenantDescription(String shopTenantDescription) {
		this.shopTenantDescription = shopTenantDescription;
	}

	public ShopTenantRegistrationId getId() {
		return id;
	}	

	public void setId(ShopTenantRegistrationId id) {
		this.id = id;
	}

	public RegistrationType getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	public void setExpectedAmount(int expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public int getExpectedAmount() {
		return expectedAmount;
	}

	public int getCurrentPaidAmount() {
		return currentPaidAmount;
	}

	public void setCurrentPaidAmount(int currentPaidAmount) {
		this.currentPaidAmount = currentPaidAmount;
	}

	public LocalDate getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(LocalDate nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "ShopTenantRegistration [id=" + id + ", dateStartRegistration=" + dateStartRegistration
				+ ", dateEndRegistration=" + dateEndRegistration + ", shopTenantDescription=" + shopTenantDescription
				+ ", registrationType=" + registrationType + ", expectedAmount=" + expectedAmount
				+ ", currentPaidAmount=" + currentPaidAmount + ", nextPaymentDate=" + nextPaymentDate + "]";
	}

}
