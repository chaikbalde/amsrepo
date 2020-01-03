package com.hub4u.ams.services;

import com.hub4u.ams.model.ShopTenantRegistration;
import com.hub4u.ams.model.ShopTenantRegistrationId;

public interface ShopTenantRegistrationService {

	ShopTenantRegistration getShopTenantRegistration(ShopTenantRegistrationId shopTenantRegitrationId);
	
	ShopTenantRegistration updateShopTenantRegistration(ShopTenantRegistration shopTenantRegitration);
	
}
