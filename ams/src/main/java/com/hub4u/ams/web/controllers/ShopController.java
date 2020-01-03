package com.hub4u.ams.web.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hub4u.ams.model.Shop;
import com.hub4u.ams.services.ShopService;
import com.hub4u.ams.web.ShopValidator;

@Controller
public class ShopController {
	
	private static Logger logger = LogManager.getLogger(ShopController.class);
	
	private ShopService shopService; 
	private ShopValidator shopValidator;
	
	public ShopController(ShopService shopService, ShopValidator shopValidator) {
		this.shopService = shopService;
		this.shopValidator = shopValidator;
	}
	
	@GetMapping("/workbench/shops")
	public String getAllShops(Model model) {
		logger.debug("getAllShops() - Fetching all Shops ...");
		List<Shop> shops = allShopsSortedByDateDesc();
		model.addAttribute("shops", shops); 
		return "workbench/shops";
	}
	
	@GetMapping("/admin/shopsadmin")
	public String getAllShopsAdmin(Model model) {
		logger.debug("getAllShopsAdmin() - Fetching all Shops by admin ...");
		List<Shop> shops = allShopsSortedByDateDesc();
		model.addAttribute("shops", shops); 
		return "admin/shopsadmin";
	}
	
	@GetMapping("/admin/shopcreateform")
	public String shopCreateForm(Model model) {
		logger.debug("shopCreateForm() - Showing Shop Create Form ...");
		model.addAttribute("shop", new Shop());
		return "admin/shopcreateform";
	}
	
	@PostMapping("/admin/shopcreate") 
	public String createShop(@Valid @ModelAttribute Shop shop, BindingResult bindingResult, Model model) {
		
		shopValidator.validate(shop, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("shops", allShopsSortedByDateDesc());
			model.addAttribute("create_error", true);
			return  "admin/shopcreateform";
		}
		
		
		shop.setDateCreated(LocalDateTime.now());
		Shop theshop = shopService.createShop(shop);
		logger.info("createShop() - Created Shop with reference: " + shop.getReference());
		model.addAttribute("shop", theshop);
		model.addAttribute("shops", allShopsSortedByDateDesc());
		model.addAttribute("create_success", true);
		return  "admin/shopsadmin";
	}
	
	@GetMapping("/admin/shopdel/{id}")
	public String deleteShop(@PathVariable Long id, Model model) {
		Shop shop = shopService.getShop(id);
		String ref = shop.getReference();
		shopService.removeShop(shop);
		logger.info("deleteShop() - Removed Shop " + ref);
		List<Shop> shops = allShopsSortedByDateDesc();
		model.addAttribute("shopref", ref);
		model.addAttribute("shops", shops);
		model.addAttribute("delete_success", true);
		return  "admin/shopsadmin";
	}
	
	@GetMapping("/admin/shopupdateform/{id}")
	public String updateShopForm(@PathVariable Long id, Model model) {
		Shop shop = shopService.getShop(id);
		logger.debug("updateShopForm() - Fetched Shop " + shop.getReference());
		model.addAttribute("shop", shop);
		return  "admin/shopupdateform";
	}
	
	@PostMapping("/admin/shopupdate")
	public String updateShop(@ModelAttribute Shop shop, Model model) {
		Shop currShop = shopService.getShopByReference(shop.getReference()).get();
		currShop.setDateModified(LocalDateTime.now());
		currShop.setDescription(shop.getDescription());
		currShop.setDimensions(shop.getDimensions());
		currShop.setOccupied(shop.isOccupied());
		currShop.setPrice(shop.getPrice());
		
		currShop.setPictureFileName(shop.getPictureFileName());
		
		// TODO
//		currShop.setTenants(tenants);
		
		shopService.updateShop(currShop);
		logger.info("updateShop() - Updated Shop " + shop.getReference());
		List<Shop> shops = allShopsSortedByDateDesc();
		model.addAttribute("shop", shop);
		model.addAttribute("shops", shops);
		model.addAttribute("update_success", true);
		
		return  "admin/shopsadmin";
	}
	
	
	/**
	 * 
	 * */
	private List<Shop> allShopsSortedByDateDesc() {
		List<Shop> shops = new ArrayList<Shop>();
		shopService.getAllShops().forEach(shops::add);
		List<Shop> shopz = shops.stream()
				.sorted(Comparator.comparing(Shop::getDateCreated).reversed())
				.collect(Collectors.toList());
		return shopz;
	}

}
