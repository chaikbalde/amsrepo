package com.hub4u.ams.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author User
 * */

@Entity
public class Payment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int amount;
	
	private LocalDateTime payDate;
	
	@ManyToOne
	private ShopTenantRegistration shopTenantRegistration;
	
	
	public Payment() {
	}

	public Payment(int amount, LocalDateTime payDate) {
		this.amount = amount;
		this.payDate = payDate;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDateTime payDate) {
		this.payDate = payDate;
	}

	public Long getId() {
		return id;
	}

	public ShopTenantRegistration getShopTenantRegistration() {
		return shopTenantRegistration;
	}

	public void setShopTenantRegistration(ShopTenantRegistration shopTenantRegistration) {
		this.shopTenantRegistration = shopTenantRegistration;
	}

	@Override
	public String toString() {
		return "Payment [amount=" + amount + ", payDate=" + payDate + "]";
	}
	
}
