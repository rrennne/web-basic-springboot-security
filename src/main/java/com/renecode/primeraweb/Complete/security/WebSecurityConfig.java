package com.renecode.primeraweb.Complete.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity // Lo activamos para trabajar de manera correcta con la seguidad de la base de datos.
public class WebSecurityConfig {

    /**
     * Esta clase nos indica para que la seguridad pueda trabajar con una seguridad en base de datos,
     * <p>La autenticación sera en base a datos de la base de datos... (los usuarios en la db)
     * */
    @Autowired
    private DataSource dataSource;

    /**
     * Lo que hacemos con esta funcion es tomar los datos desde un db, usuario y contraseña y el rol.
     * */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
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
