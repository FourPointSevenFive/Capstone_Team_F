package com.hallyugo.hallyugo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HallyugoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HallyugoApplication.class, args);
	}

}
