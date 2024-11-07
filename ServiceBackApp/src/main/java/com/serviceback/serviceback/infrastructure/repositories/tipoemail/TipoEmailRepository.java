package com.serviceback.serviceback.infrastructure.repositories.tipoemail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.TipoEmail;

@Repository
public interface TipoEmailRepository extends JpaRepository<TipoEmail, Integer> {
    boolean existsByNombre(String nombre);
}
