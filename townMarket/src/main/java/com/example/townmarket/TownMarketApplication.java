package com.example.townmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TownMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TownMarketApplication.class, args);
	}

}
