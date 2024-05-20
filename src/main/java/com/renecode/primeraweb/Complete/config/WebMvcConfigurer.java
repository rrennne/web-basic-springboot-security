package com.renecode.primeraweb.Complete.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
/**
 * Declaramos con @Configuration que es una clase de configuración <p>
 * Indicamos que esta clase en una fuente de beans para la aplicación de Spring
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {


    /**
     * Sobreescribimos el método addViewControllers de la Interface que implementamos desde la clase ~.WebMvcConfigurer
     * <p>Este método nos va a permitir registrar controladores de vistas personalizados. (Controlador de vista es un patrón de diseño) que simplifica la gestión de respuestas HTTP simples
     * como páginas de error, sin necesidad de crear un controlador completo.
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/login").setViewName("login"); // Cada vez que queramos crear una nueva vista personalizada la creamos asi.
    }
}
