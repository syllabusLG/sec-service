package org.sid;

import java.util.stream.Stream;

import org.sid.entities.AppRole;
import org.sid.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args ->{
			accountService.saveRole(new AppRole(null, "ADMIN"));
			accountService.saveRole(new AppRole(null, "USER"));
			Stream.of("user1", "user2", "user3", "admin").forEach(userna->{
				accountService.saveUser(userna, "1234", "1234");
			});
			accountService.addRoleToUser("admin", "ADMIN");
		};
		
	}
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
