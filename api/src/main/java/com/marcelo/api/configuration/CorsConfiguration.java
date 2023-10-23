package com.marcelo.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplicar a configuração a todas as URLs
                .allowedOrigins("http://localhost:4200") // Origens permitidas
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*") // Cabeçalhos permitidos
                .allowCredentials(true); // Permitir credenciais (cookies)
    }
}
