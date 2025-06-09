package org.demo.kaiaskin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories(basePackages = "org.demo.kaiaskin.repositories.mysql")
@EnableMongoRepositories(basePackages = "org.demo.kaiaskin.repositories.mongodb")
@SpringBootApplication
public class KaiaSkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaiaSkinApplication.class, args);
	}

}
