package com.renecode.primeraweb.Complete;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPassword {

    /**
     * Esta clase es para encriptar una contraseña.
     * Se podria cambiar para modicar contraseñas apenas de registran algun usuario.
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword); // encripto la contraseña

        System.out.println(encodedPassword); // Se muestra la contraseña encriptada para verificar que all este bien.
    }
}
