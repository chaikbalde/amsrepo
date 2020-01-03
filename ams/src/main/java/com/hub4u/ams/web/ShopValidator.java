package com.hub4u.ams.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hub4u.ams.model.Shop;
import com.hub4u.ams.services.ShopService;

@Component
public class ShopValidator implements Validator {

	private ShopService shopService;
	
	public ShopValidator(ShopService shopService) {
		this.shopService = shopService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Shop.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Shop shop = (Shop)target;
		if (shopService.getShopByReference(shop.getReference()).isPresent()) {
			errors.rejectValue("reference", "shop.reference.duplicate");
		} 
	}



}
