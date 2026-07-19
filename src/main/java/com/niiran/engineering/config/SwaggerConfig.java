package com.niiran.engineering.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springAI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Spring AI API")
                        .description("REST APIs for Spring AI APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Pravendra Kumar")
                                .email("pravendra@gmail.com")));
    }
}
