package com.cydeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients //needed for FeignClient
public class Spring17RestConsumingApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring17RestConsumingApisApplication.class, args);
	}

	//third party classes are always added in runner class as a bean
	//this bean is consuming api from 3rd parties
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
