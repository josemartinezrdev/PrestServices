package com.serviceback.serviceback.infrastructure.repositories.emailpersona;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceback.serviceback.domain.entities.EmailPersona;

public interface EmailPersonaRepository extends JpaRepository<EmailPersona, Integer> {
    boolean existsByEmail(String email);
}
