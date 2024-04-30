package com.renecode.primeraweb.Complete.repository;

import com.renecode.primeraweb.Complete.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // * Este es un ESTEREOTIPO.
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

}
