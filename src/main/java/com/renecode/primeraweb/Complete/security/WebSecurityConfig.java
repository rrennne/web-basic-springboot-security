package com.renecode.primeraweb.Complete.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    /**
     * Como no tenemos una base de datos vamos a guardar los detalles de los usuarios en memoria
     * */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails userDetails1 = User.builder()
                .username("user1") /* Cuando hacemos las pruebas nos tocara cambiar el .username(1,2,3)*/
                .password("{bcrypt}$2a$10$CUMwMj9AJsdqI5mZv6.6PuumzvxCk1Hd3jnlSL2eM6q08vtO0y3FK") // Hay varios métodos para asignar las contraseñas encriptadas, usaremos este.
                .roles("USER")
                .build();

        UserDetails userDetails2 = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$CUMwMj9AJsdqI5mZv6.6PuumzvxCk1Hd3jnlSL2eM6q08vtO0y3FK") // Hay varios métodos para asignar las contraseñas encriptadas, usaremos este.
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Con esto vamos a tener los endpoints que vamos a autorizar
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/personas").permitAll()
                        .requestMatchers("/personas/nuevaPersona").hasAnyRole("ADMIN")
                        .requestMatchers("/personas/editar/*", "/personas/eliminar/*").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }

}
