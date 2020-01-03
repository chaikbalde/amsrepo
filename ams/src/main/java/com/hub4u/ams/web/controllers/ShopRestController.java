/**
 * 
 */
package com.hub4u.ams.web.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hub4u.ams.model.Shop;
import com.hub4u.ams.services.ShopService;

import lombok.extern.slf4j.Slf4j;

/**
 * <code>RestController</code> to handle requests to <tt>Shop</tt> related activities
 * 
 * @author User
 *
 */
@Slf4j
@RestController
public class ShopRestController {
	
	private ShopService shopService;

	/**
	 * 
	 */
	public ShopRestController(ShopService shopService) {
		this.shopService = shopService; 
	}
	
	/**
	 * 
	 * */
	@GetMapping("/workbench/restshops")
	public List<Shop> getAllShops() {
		log.debug("getAllShops() - Fetching All shops");
		return allShopsSortedByDateDesc(shopService.getAllShops());
	}
	
	/**
	 * 
	 * */
	@PostMapping("/admin/restshops")
	public List<Shop> createNewShop(@Valid @RequestBody Shop shop) {
		log.info("createNewShop() - Creating new Shop object with reference : " + shop);
		shopService.createShop(shop);
		return allShopsSortedByDateDesc(shopService.getAllShops());
	}
	 
	/**
	 * 
	 * */
	@DeleteMapping("/admin/restshops/{id}") 
	public List<Shop> deleteShop(@PathVariable Long id) {
		shopService.removeShop(id);
		return allShopsSortedByDateDesc(shopService.getAllShops());
	}

	
	///
	private List<Shop> allShopsSortedByDateDesc(Iterable<Shop> shopsIter) {
		List<Shop> shops =  StreamSupport.stream(shopsIter.spliterator(), false)
				.sorted(Comparator.comparing(Shop::getDateCreated).reversed())
				.collect(Collectors.toList());
		return shops;
	}

}
