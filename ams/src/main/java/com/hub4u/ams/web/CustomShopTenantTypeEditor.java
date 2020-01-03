package com.hub4u.ams.web;

import java.beans.PropertyEditorSupport;

import com.hub4u.ams.model.ShopTenantRegistration;

public class CustomShopTenantTypeEditor extends PropertyEditorSupport {

	public CustomShopTenantTypeEditor() {
	}

	public CustomShopTenantTypeEditor(Object arg0) {
		super(arg0);
	}
	
	@Override
	public String getAsText() {
		ShopTenantRegistration shopTenant = (ShopTenantRegistration) getValue();
		return (shopTenant == null) ? "" : shopTenant.getShop().getReference(); 
	}
	
//	@Override
//	public void setAsText(String arg0) throws IllegalArgumentException {
//		// TODO Auto-generated method stub
//		super.setAsText(arg0);
//	}

}
