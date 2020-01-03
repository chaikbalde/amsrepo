package com.hub4u.ams.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hub4u.ams.model.Shop;
import com.hub4u.ams.model.Tenant;

@Service
public class TenantServiceImpl implements TenantService {
	
	private TenantRepository tenantRepository;

	
	public TenantServiceImpl(TenantRepository tenantRepository) {
		this.tenantRepository = tenantRepository;
	}

	@Override
	@Transactional
	public Tenant registerTenant(Tenant tenant) {
		return tenantRepository.save(tenant);
	}
	
	/**
	 * Tenant has already been registered, but want another shop
	 * */
	@Override
	@Transactional
	public void registerShopForTenant(Shop shop, Tenant tenant) {
//		tenant.getShops().add(shop);
//		shop.setTenant(tenant);
//		shop.setOccupied(true);
//		tenantRepository.save(tenant);
//		logger.info("registerTenantToShop() - Tenant '" +tenant.getId()+ "' has been registered with Shop '"+shop.getReference()+"' ");
	}
	
	@Override
	public Iterable<Tenant> getAllTenants() {
		return tenantRepository.findAll() ;
	}

	@Override
	public Tenant getTenant(Long id) {
		return tenantRepository.findById(id).get() ;
	}

}
