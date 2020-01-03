package com.hub4u.ams.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hub4u.ams.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRoleName(String roleName);
}
