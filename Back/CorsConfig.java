package com.DesarrolloWEB_crud.demopostgres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Esto permite solicitudes desde cualquier origen
        config.addAllowedMethod("*"); // Esto permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
        config.addAllowedHeader("*"); // Esto permite todos los encabezados en las solicitudes
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
