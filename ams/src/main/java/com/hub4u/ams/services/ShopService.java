package com.hub4u.ams.services;

import java.util.Optional;

import com.hub4u.ams.model.Shop;

public interface ShopService {

	Shop createShop(Shop shop);
	
	Shop updateShop(Shop shop);
	
	Iterable<Shop> getAllShops();
	
	Shop getShop(Long id);
	
	Optional<Shop> getShopByReference(String reference);
	
	void removeAllShops();
	
	void removeShop(Shop shop);
	
	void removeShop(Long id);
}
