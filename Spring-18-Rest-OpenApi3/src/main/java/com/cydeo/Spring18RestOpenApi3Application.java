package com.cydeo;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Spring18RestOpenApi3Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring18RestOpenApi3Application.class, args);
	}

	//3rd party dependency. we need this bean to modify our swagger documentation
	//for example we add info below that will reflect in our custom swagger doc for this project: http://localhost:8080/swagger-custom.html
	//we can also add servers, post/put/get/delete, urls, paths and etc.
	@Bean
	public OpenAPI customOpenApi(){
		return new OpenAPI()
				.info(new Info().title("Cinema Application OpenAPI").version("v1").description("Cinema application API documentation"));
	}

}
