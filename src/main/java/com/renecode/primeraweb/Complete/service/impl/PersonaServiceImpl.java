package com.renecode.primeraweb.Complete.service.impl;

import com.renecode.primeraweb.Complete.entities.Persona;
import com.renecode.primeraweb.Complete.repository.IPersonaRepository;
import com.renecode.primeraweb.Complete.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Override
    public long contarPersonas() {
        return iPersonaRepository.count();
    }

    @Override
    public List<Persona> obtenerTodas() {
        return iPersonaRepository.findAll();
    }

    @Override
    public Persona obtenerPorId(Long id) {
        return iPersonaRepository.findById(id).orElse(null);
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return iPersonaRepository.save(persona);
    }

    @Override
    public Persona actualizarPersona(Long id, Persona persona) {
        Persona personaBBDD = iPersonaRepository.findById(id).orElse(null);
        if (personaBBDD != null) {
            personaBBDD.setNombre(persona.getNombre());
            personaBBDD.setEdad(persona.getEdad());

            // Si existe esa persona retornara la persona con los datos actualizados.
            return iPersonaRepository.save(personaBBDD);
        }
        // Si no existe entonces retorna null.
        return null;
    }

    @Override
    public void eliminarPersona(Long id) {
        iPersonaRepository.deleteById(id);
    }
}
