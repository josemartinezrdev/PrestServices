package com.serviceback.serviceback.infrastructure.repositories.telpersona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.TelefonoPersona;

@Repository
public interface TelefonoPersonaRepository extends JpaRepository<TelefonoPersona, Integer> {

}
