package com.policymanagement.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title("Policy Management System API")
						.description("API documentation for Policy Management System").version("1.0")
						.contact(new Contact().name("Your Name").email("your.email@example.com"))
						.license(new License().name("Apache 2.0")
								.url("http://www.apache.org/licenses/LICENSE-2.0.html")))
				.components(new Components().addSecuritySchemes("bearer-jwt",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
								.in(SecurityScheme.In.HEADER).name("Authorization")))
				.addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")))
				.servers(Arrays.asList(new Server().url("http://localhost:8080").description("Local server")));
	}
}
//eclipse-workspace/policymanagement/policymanagement