package com.hub4u.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hub4u.ams.model.Shop;

@Service
public class ShopServiceImpl implements ShopService {
	
	private ShopRepository shopRepository;
	
	public ShopServiceImpl(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}


	@Override
	public Shop createShop(Shop shop) {
		return shopRepository.save(shop) ;
	}

	@Override
	public Shop updateShop(Shop shop) {
		return shopRepository.save(shop);
	}
	
	@Override
	public Iterable<Shop> getAllShops() {
		return shopRepository.findAll();
	}
	
	@Override
	public void removeAllShops() {
		shopRepository.deleteAll();
	}
	
	@Override
	public Shop getShop(Long id) {
		return shopRepository.findById(id).get();
	}
	
	@Override
	public Optional<Shop> getShopByReference(String reference) {
		return shopRepository.findByReference(reference);
	}
	
	@Override
	public void removeShop(Long id) {
		shopRepository.deleteById(id);
	}
	
	@Override
	public void removeShop(Shop shop) {
		shopRepository.delete(shop);
	}
	
	@Override
	public List<Shop> getOccupiedShops() {
		return this.shopRepository.findByOccupied();
	}

}
