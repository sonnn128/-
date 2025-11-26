package com.sieuvjp.greenbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreenbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenbookApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.boot.CommandLineRunner run(com.sieuvjp.greenbook.service.UserService userService, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
		return args -> {
			if (!userService.existsByUsername("admin")) {
				com.sieuvjp.greenbook.entity.User admin = new com.sieuvjp.greenbook.entity.User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setEmail("admin@greenbook.com");
				admin.setRole(com.sieuvjp.greenbook.enums.Role.ADMIN);
				admin.setActive(true);
				userService.save(admin);
				System.out.println("Admin account created: admin / admin");
			}
		};
	}
}