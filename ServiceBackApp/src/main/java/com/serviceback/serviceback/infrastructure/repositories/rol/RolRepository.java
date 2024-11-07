package com.serviceback.serviceback.infrastructure.repositories.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.security.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    boolean existsByNombre(String nombre);
    Rol findByNombre(String nombre);
}
