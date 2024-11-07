package com.serviceback.serviceback.infrastructure.repositories.tipopersona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.TipoPersona;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersona, Integer> {
    boolean existsByNombre(String nombre);
}
