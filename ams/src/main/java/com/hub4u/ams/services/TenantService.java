package com.hub4u.ams.services;

import com.hub4u.ams.model.Shop;
import com.hub4u.ams.model.Tenant;

public interface TenantService {
	
	Tenant registerTenant(Tenant tenant);
	
	void registerShopForTenant(Shop shop, Tenant tenant);
	
	Iterable<Tenant> getAllTenants();
	
	Tenant getTenant(Long id);
	
}
