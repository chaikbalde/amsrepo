package com.hub4u.ams.web.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hub4u.ams.model.Payment;
import com.hub4u.ams.model.RegistrationType;
import com.hub4u.ams.model.Shop;
import com.hub4u.ams.model.ShopTenantRegistration;
import com.hub4u.ams.model.ShopTenantRegistrationId;
import com.hub4u.ams.model.Tenant;
import com.hub4u.ams.services.ShopService;
import com.hub4u.ams.services.ShopTenantRegistrationService;
import com.hub4u.ams.services.TenantService;
import com.hub4u.ams.web.CustomShopTenantTypeEditor;

@Controller
public class TenantController {
	
	private static Logger logger = LogManager.getLogger(TenantController.class);
	
	private ShopService shopService;
	private TenantService tenantService;
	private ShopTenantRegistrationService shopTenantRegistrationService;
	
	
	public TenantController(ShopService shopService, TenantService tenantService, ShopTenantRegistrationService shopTenantRegistrationService) {
		this.shopService = shopService;
		this.tenantService = tenantService;
		this.shopTenantRegistrationService = shopTenantRegistrationService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ShopTenantRegistration.class, new CustomShopTenantTypeEditor());
	}
	
	/**
	 * 
	 * */
	@GetMapping("/admin/tenantregisterform")
	public String tenantRegistrationForm(Model model) {
		logger.debug("tenantRegistrationForm() - Tenant Registration Form");
		model.addAttribute("shopz", allUnoccupiedShops());
		model.addAttribute("tenant", new Tenant());
		return "admin/tenantregisterform";
	}
	
	/**
	 * 
	 * */
	@Transactional
	@PostMapping("/admin/tenantregister")
	public String registerTenant(@ModelAttribute Tenant tenantForm, Model model) {
		logger.debug("registerTenant-() - Registering tenant...");
		LocalDateTime now = LocalDateTime.now();
		tenantForm.setDateCreated(now);
		tenantForm.setActive(true);
		Tenant tenant = tenantService.registerTenant(tenantForm);
	
		assignShopToTenant(tenant, tenantForm);
		
		logger.info("registerTenant() - Registered Tenant '"+tenant.getLastName()+"' to Shop '"+tenant.getShops().get(0).getShop().getReference()+"'  ");
		
		model.addAttribute("tenants", allTenantsSortedByDateDesc());
		return "admin/tenantsadmin";
	}
	
	/**
	 * 
	 * */
	@GetMapping("/admin/tenantsadmin") 
	public String getAllTenantsAdmin(Model model) {
		logger.debug("getAllTenantsAdmin() - Fetching all tenants");
		model.addAttribute("tenants", allTenantsSortedByDateDesc());
		return "admin/tenantsadmin";
	}
	
	/**
	 * 
	 * */
	@GetMapping("/workbench/tenants") 
	public String getAllTenants(Model model) {
		logger.debug("getAllTenants() - Fetching all tenants");
		model.addAttribute("tenants", allTenantsSortedByDateDesc());
		return "workbench/tenants";
	}
	
	/**
	 * 
	 * */
	@GetMapping("/workbench/tenants/{id}") 
	public String getTenant(@PathVariable Long id, Model model) {
		logger.debug("getTenant() - Fetching Tenant with id: " + id);
		Tenant tenant = tenantService.getTenant(id);
		model.addAttribute("tenant", tenant);
		return "workbench/tenantdetails";
	}
	
	
	/**
	 * 
	 * */
	@GetMapping("/admin/tenantupdateform/{id}")
	public String updateTenantForm(@PathVariable Long id, Model model) {
		
		Tenant tenant = tenantService.getTenant(id);
		
		logger.debug("updateTenantForm() - Fetched Tenant " + tenant.getLastName() + " " + tenant.getFirstName());
		
		model.addAttribute("shopTenants", tenant.getShops());
		model.addAttribute("tenant", tenant);
		
		return "admin/tenantupdateform";
	}
	
	/**
	 *  
	 * */
	@PostMapping("/workbench/payment/{shopId}/{tenantId}")
	public String makePayment(@PathVariable Long shopId, @PathVariable Long tenantId, HttpServletRequest httpServletRequest,  Model model) {
		
		logger.debug("makePayment() - Intro ... ");
		String payAmountStr = httpServletRequest.getParameter("payAmount");
		if (StringUtils.isNumeric(payAmountStr)) {
			Integer amount = Integer.valueOf(payAmountStr);
			
			Payment payment = new Payment(amount, LocalDateTime.now());
			
			ShopTenantRegistrationId stRegId = new ShopTenantRegistrationId(shopId, tenantId);
			ShopTenantRegistration shopTenantRegistration =  shopTenantRegistrationService.getShopTenantRegistration(stRegId);
			
			shopTenantRegistration.getPayments().add(payment);
			payment.setShopTenantRegistration(shopTenantRegistration);
			
			shopTenantRegistration.setCurrentPaidAmount(shopTenantRegistration.getCurrentPaidAmount() + amount);
			
			shopTenantRegistrationService.updateShopTenantRegistration(shopTenantRegistration);
			logger.debug("makePayment() - Made Payment. Amount : " + amount);
			
			model.addAttribute("shopTenantRegistration", shopTenantRegistration);
			model.addAttribute("shop", shopService.getShop(shopId));
			model.addAttribute("tenant", tenantService.getTenant(tenantId));
			model.addAttribute("payment_success", true);
			
			
		} else {
			logger.error("makePayment() - >>> Amount to pay must be a numeric value: " + payAmountStr);
			
		}
		
		
		model.addAttribute("tenants", allTenantsSortedByDateDesc());
		return "workbench/tenants";
	}
	
	
	
	/**
	 * 
	 * */
	private List<Tenant> allTenantsSortedByDateDesc() {
		Iterable<Tenant> tenantItr = tenantService.getAllTenants();
		List<Tenant> tenants = new ArrayList<Tenant>();
		tenantItr.forEach(tenants::add);
		List<Tenant> tnts = tenants.stream()
				.sorted(Comparator.comparing(Tenant::getDateCreated).reversed())
				.collect(Collectors.toList());
		return tnts;
	}
	
	/**
	 * 
	 * */
	private List<Shop> allUnoccupiedShops() {
		List<Shop> shopsList = new ArrayList<Shop>();
		shopService.getAllShops().forEach(shopsList::add);
		return shopsList.stream()
			.filter(s -> !s.isOccupied())
			.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * */
	private void assignShopToTenant(Tenant tenant, Tenant tenantForm) {
		LocalDateTime now = LocalDateTime.now();
		String shopIdStr =  tenantForm.getShopSelect();
		Shop shop = shopService.getShop(Long.valueOf(shopIdStr)); // TODO - Check Validation
		
		shop.setOccupied(true);
		shop.setDateModified(now);
		
		ShopTenantRegistrationId shopTenantId = new ShopTenantRegistrationId(shop.getId(), tenant.getId());
		ShopTenantRegistration shopTenant = new ShopTenantRegistration();
		shopTenant.setId(shopTenantId);
		shopTenant.setShop(shop);
		shopTenant.setTenant(tenant);
		
		shopTenant.setDateStartRegistration(LocalDate.now());
		shopTenant.setShopTenantDescription(tenantForm.getShopDescription());
		
		RegistrationType regType = RegistrationType.valueOf(tenantForm.getRegTypeSelect()); // TODO - Validate
		shopTenant.setRegistrationType(regType);
		int exptAmount = expectedAmount(shop, regType);
		shopTenant.setExpectedAmount(exptAmount); 
		int paidAmount = tenantForm.getPaidAmount();
		
		shopTenant.setCurrentPaidAmount(paidAmount);
		
//		shopTenant.setRestToPay(exptAmount - paidAmount); 
		
 		shopTenant.setNextPaymentDate(nextPaymentDate(null, regType)); 
		
		Payment payment = new Payment(); 
		payment.setAmount(paidAmount);
		payment.setPayDate(now);
		
		payment.setShopTenantRegistration(shopTenant);
		shopTenant.getPayments().add(payment);
		
		shop.getTenants().add(shopTenant);
		tenant.getShops().add(shopTenant);
	}
	
	/**
	 * 
	 * */
	private int expectedAmount(Shop shop, RegistrationType regType) {
		int amount = 0;
		switch (regType) {
			case MONTHLY:
				amount = shop.getPrice();
			case QUARTERLY:
				amount = shop.getPrice() * 3;
				break;
	
			case HALFYEARLY:
				amount = shop.getPrice() * 6;
				break;
	
			case YEARLY:
				amount = shop.getPrice() * 12;
				break;
	
			default:
				logger.error(">>> Unknown RegistrationType : " + regType);
				break;
		}
		return amount;
	}
	
	/**
	 * 
	 * */
	private LocalDate nextPaymentDate(LocalDate lastDueDate, RegistrationType regType) {
		
		LocalDate today = LocalDate.now();
		
		LocalDate nextDate = today;
		
		switch (regType) {
			case MONTHLY:
				nextDate = today.plus(1, ChronoUnit.MONTHS);
				break;
	
			case QUARTERLY:
				nextDate = today.plus(3, ChronoUnit.MONTHS);
				break;
	
			case HALFYEARLY:
				nextDate = today.plus(6, ChronoUnit.MONTHS);
				break;
	
			case YEARLY:
				nextDate = today.plus(1, ChronoUnit.YEARS);
				break;
				
			default:
				logger.error(">>> Unknown RegistrationType : " + regType);
				break;
		}

		return nextDate;
	}

}
