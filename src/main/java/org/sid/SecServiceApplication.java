package org.sid;

import java.util.stream.Stream;

import org.sid.entities.AppRole;
import org.sid.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			Stream.of("user1", "user2", "user3", "admin").forEach(username->{
				accountService.saveUser(username, "1234", "1234");
			});
		};
		
	}

}
