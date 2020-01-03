package com.hub4u.ams.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hub4u.ams.model.ShopTenantRegistration;
import com.hub4u.ams.model.ShopTenantRegistrationId;

@Repository
public interface ShopTenantRegistrationRepository extends CrudRepository<ShopTenantRegistration, ShopTenantRegistrationId> {

}
