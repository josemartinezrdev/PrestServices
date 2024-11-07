package com.serviceback.serviceback.infrastructure.repositories.persona;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {
    boolean existsByNombre(String nombre);

    Persona findByUserUsername(String username);
    
    @Query("SELECT p FROM Persona p JOIN p.user u WHERE u.role.id = :id")
    List<Persona> findByRole(@Param("id") int id);
}

