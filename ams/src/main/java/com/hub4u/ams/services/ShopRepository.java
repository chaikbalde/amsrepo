package com.hub4u.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hub4u.ams.model.Shop;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

	Optional<Shop> findByReference(String reference); 
	
	List<Shop> findByOccupied();
}
