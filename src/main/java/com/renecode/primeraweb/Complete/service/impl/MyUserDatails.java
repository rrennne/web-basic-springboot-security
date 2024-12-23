package com.renecode.primeraweb.Complete.service.impl;

import com.renecode.primeraweb.Complete.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Lo que logramos cuando implementamos la Inteface UserDatails es :
 * representar la informacion basica de un usuario, y su implementaci&oacute;n es clave
 * para mejorar la autenticaci&oacute;n y autorizaci&oacute;n en aplicaciones de SpringBoot.
 * */
@AllArgsConstructor
public class MyUserDatails implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
