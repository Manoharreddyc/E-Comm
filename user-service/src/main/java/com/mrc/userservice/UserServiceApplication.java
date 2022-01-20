package com.mrc.userservice;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import	org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	public GroupedOpenApi adminApi() {
		return GroupedOpenApi.builder().group("com.mrc").pathsToMatch("/**").build();
	}



	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Ekart").version("1.0.0").license(new License().
						name("Ekart").url("http://ekart.com")))
				.externalDocs(new ExternalDocumentation().description("Ecommerce"));
	}

}
