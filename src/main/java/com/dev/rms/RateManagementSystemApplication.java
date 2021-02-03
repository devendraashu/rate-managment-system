package com.dev.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
public class RateManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateManagementSystemApplication.class, args);
	}

}
