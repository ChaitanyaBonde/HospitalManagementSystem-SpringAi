package com.example.HMS_AI.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApiConfig(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                        .addSecuritySchemes("apiKeyAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("SEC-API-KEY"))
                )
                .info(new Info()
                        .title("HMS API")
                        .description("Hospital Management System - Spring AI APIs")
                )
                .tags(List.of(
                        new Tag().name("1. Authentication API"),
                        new Tag().name("2. Admin APIs"),
                        new Tag().name("3. Doctor APIs"),
                        new Tag().name("4. Patient APIs"),
                        new Tag().name("5. Appointment APIs")
                ));

    }
}
