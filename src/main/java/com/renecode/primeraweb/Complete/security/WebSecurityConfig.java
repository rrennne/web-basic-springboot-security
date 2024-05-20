package com.renecode.primeraweb.Complete.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    /**
     * Regisstramos nuestro codificador en la fabrica de Spring
     * para poder codificar la contraseñas en toda la aplicacion
     * como en la clase InmemoryUserDetailsManager donde llamamos el método  .password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Guardaremos los detalles de usuario en memoria, asi no tendremos que usar una ddbb.
     * Tenemos definidos 2 usuarios con roles distintos USER, ADMIN.
     * Al final creamos una nueva isntancia de {@param InMemoryUserDetailsManager} y le pasamos los dos objetos que hemos creado.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails userDetails1 = User.builder()
                .username("user7") /* Cuando hacemos las pruebas nos tocara cambiar el .username(1,2,3)*/
                .password("$2a$10$CUMwMj9AJsdqI5mZv6.6PuumzvxCk1Hd3jnlSL2eM6q08vtO0y3FK") // Hay varios métodos para asignar las contraseñas encriptadas, usaremos este.
                .roles("USER")
                .build();

        UserDetails userDetails2 = User.builder()
                .username("admin5")
                .password("$2a$10$CUMwMj9AJsdqI5mZv6.6PuumzvxCk1Hd3jnlSL2eM6q08vtO0y3FK") // Hay varios métodos para asignar las contraseñas encriptadas, usaremos este.
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    /**
     * Aqui es donde definimos cómo se manejaran las solicitudes HTTP en función
     * de los roles de los usuarios y las rutas a las que acceden.
     * {@Param HttpSecurity} este objeto es el que nos permite configurar la seguridad de la aplicacion.
     * es quien nos proporciona métodos para configurar aspectos especificos de la seguirdad, como la autenticación,
     * y la autorización, y el menejo de excepciones.
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /* Manejamos la cadena de filtros con HttpSecurity */
        httpSecurity.authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/personas").permitAll()
                                .requestMatchers("/personas/nuevaPersona").hasAnyRole("ADMIN")
                                .requestMatchers("/personas/editar/*", "/personas/eliminar/*").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())

                        .formLogin(form -> form
                                .loginPage("/login")
                                .permitAll()) /* Eliminamos la manera anterir de registrarnos anterior y ahora nosotros la vamos a manejar */
                        .logout(l -> l.permitAll()) /* Ahora tendremos un cierre de secion */
                        .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build(); // Construimos la cadena de filtros de seguridad basada en la configuracion que he definido.
                                     // Se crea una instancia de SecurityFilerChain aplicando la cadena de filtros.
    }                                // SecurityFilerChain representa la secuencia de filtros que se aplicarán a las solicitudes HTTP entrantes.
}
