package com.example.be.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Model Training API")
                    .description("API for managing ML model training and datasets")
                    .version("1.0")
                    .contact(
                        Contact()
                            .name("PTTK Team")
                            .email("example@example.com")
                    )
                    .license(
                        License()
                            .name("MIT License")
                    )
            )
    }
} 