package com.hub4u.ams.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShopTenantRegistrationId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "shop_id")
	private Long shopId;
	
	@Column(name = "tenant_id")
	private Long tenantId;
	
	public ShopTenantRegistrationId() {
	}

	public ShopTenantRegistrationId(Long shopId, Long tenantId) {
		this.shopId = shopId;
		this.tenantId = tenantId;
	}
	
	@Override
	public int hashCode() {
		return (int) (this.shopId + this.tenantId) ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShopTenantRegistrationId) {
			ShopTenantRegistrationId otherId = (ShopTenantRegistrationId) obj;
			return (otherId.tenantId == this.tenantId) && (otherId.shopId == this.shopId);
		}
		return false;
	}
}
