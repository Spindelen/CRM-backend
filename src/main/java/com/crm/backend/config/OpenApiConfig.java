package com.crm.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger configuration.
 * Defines API documentation metadata and accessibility.
 */
@Slf4j
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        log.debug("Initializing OpenAPI schema");
        
        return new OpenAPI()
                .info(new Info()
                        .title("CRM Backend API")
                        .version("1.0.0")
                        .description("CRM backend API for internship project")
                        .contact(new Contact()
                                .name("CRM Team")
                                .url("http://localhost:8080"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
