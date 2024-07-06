package org.example.rickandmortymobdevchallenge.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rick & Morty Mobdev Challenge")
                        .version("1.0.0")
                        .description("Documentation for all endpoints related to Rick & Morty Mobdev Challenge."));
    }
}
