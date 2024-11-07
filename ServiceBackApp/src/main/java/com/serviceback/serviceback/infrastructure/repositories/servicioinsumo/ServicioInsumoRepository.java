package com.serviceback.serviceback.infrastructure.repositories.servicioinsumo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.ServicioInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.ServicioInsumoPK;

@Repository
public interface ServicioInsumoRepository extends JpaRepository<ServicioInsumo, ServicioInsumoPK> {

}
