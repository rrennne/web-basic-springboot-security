package com.renecode.primeraweb.Complete.service;

import com.renecode.primeraweb.Complete.entities.Persona;

import java.util.List;

public interface IPersonaService {

    long contarPersonas();

    List<Persona> obtenerTodas();

    Persona obtenerPorId(Long id);

    Persona crearPersona(Persona persona);

    Persona actualizarPersona(Long id, Persona persona);

    void eliminarPersona(Long id);

}
