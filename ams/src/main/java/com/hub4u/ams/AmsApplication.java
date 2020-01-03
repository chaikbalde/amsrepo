package com.hub4u.ams;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.hub4u.ams.model.Role;
import com.hub4u.ams.model.UserAccount;
import com.hub4u.ams.services.RoleRepository;
import com.hub4u.ams.services.UserAccountRepository;
import com.hub4u.ams.services.UserAccountService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class AmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmsApplication.class, args);
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}	
	
	
//	@Bean
//	CommandLineRunner init(ShopService shopService) {
//		return args -> {
//			shopService.removeAllShops();
//			List<Shop> shops = Arrays.asList(
//					new Shop(1L, "MGZN 012", "15x4", new BigDecimal(250000), "Vêtements made in USA", LocalDateTime.of(LocalDate.of(2018, 11, 23), LocalTime.of(13, 30))),
//					new Shop(2L, "MGZN 023", "20x5", new BigDecimal(280000), "Pagnes Wax", LocalDateTime.of(LocalDate.of(2018, 9, 18), LocalTime.of(11, 22))),
//					new Shop(3L, "BTQ 045", "10x5", new BigDecimal(220000), "Chaussures italiennes", LocalDateTime.of(LocalDate.of(2018, 4, 3), LocalTime.of(15, 11))),
//					new Shop(4L, "MGZN 067", "10x3", new BigDecimal(100000), "Sacs Fripperie", LocalDateTime.of(LocalDate.of(2018, 2, 23), LocalTime.of(13, 45)))
//					); 
//			shops.stream().forEach(s -> shopService.createShop(s));
//			System.out.println("\n====================");
//			shopService.getAllShops().forEach(System.out::println);
//			
//		};
//		
//	}
	
	@Bean
	CommandLineRunner initDB(RoleRepository repository, UserAccountService userAccountService) {
		
		return args -> {
//			log.info("initDB() - Preloading Role : " + repository.save(new Role("ROLE_ADMIN")) );
//			log.info("initDB() - Preloading Role : " + repository.save(new Role("ROLE_MANAGER")) );
//			log.info("initDB() - Preloading Role : " + repository.save(new Role("ROLE_ACCOUNTING")) );
//			log.info("initDB() - Preloading Role : " + repository.save(new Role("ROLE_STAFF")) );
//			
//			String password = new BCryptPasswordEncoder().encode("admin");
//			log.info("initDB() - Preloading 'admin' UserAccount : ... \n"); 
//			
//			UserAccount userAccount = new UserAccount("Admin", "Admin", "admin", password, true, LocalDateTime.now());
//			userAccount.getRoles().add(repository.findByRoleName("ROLE_ADMIN"));
//			userAccountService.createUserAccount(userAccount);
			
		};
	}

	
}
