package me.elamranioussama.exam_springboot_angular.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI creditManagementOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Credit Management API")
                .description("REST API for managing bank credits")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Oussama EL AMRANI")
                    .email("elamranioussama01@gmail.com")));
    }
}
