package com.hub4u.ams.services;

import org.springframework.data.repository.CrudRepository;

import com.hub4u.ams.model.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Long> {

}
