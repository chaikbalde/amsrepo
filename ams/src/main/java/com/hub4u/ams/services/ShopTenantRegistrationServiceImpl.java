package com.hub4u.ams.services;

import org.springframework.stereotype.Service;

import com.hub4u.ams.model.ShopTenantRegistration;
import com.hub4u.ams.model.ShopTenantRegistrationId;

@Service
public class ShopTenantRegistrationServiceImpl implements ShopTenantRegistrationService {
	
	ShopTenantRegistrationRepository repository;
	
	public ShopTenantRegistrationServiceImpl(ShopTenantRegistrationRepository repository) {
		this.repository = repository;
	}

	@Override
	public ShopTenantRegistration getShopTenantRegistration(ShopTenantRegistrationId shopTenantRegitrationId) {
		return repository.findById(shopTenantRegitrationId).get();
	}
	
	@Override
	public ShopTenantRegistration updateShopTenantRegistration(ShopTenantRegistration shopTenantRegitration) {
		return repository.save(shopTenantRegitration);
	}

}
