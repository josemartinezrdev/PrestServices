package com.serviceback.serviceback.infrastructure.repositories.tipoempresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.TipoEmpresa;

@Repository
public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresa, Integer> {

}
