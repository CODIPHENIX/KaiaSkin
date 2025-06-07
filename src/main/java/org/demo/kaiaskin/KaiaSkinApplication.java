package org.demo.kaiaskin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KaiaSkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaiaSkinApplication.class, args);
	}

}
