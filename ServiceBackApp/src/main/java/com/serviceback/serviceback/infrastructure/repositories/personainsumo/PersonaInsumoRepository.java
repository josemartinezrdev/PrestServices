package com.serviceback.serviceback.infrastructure.repositories.personainsumo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.Insumo;
import com.serviceback.serviceback.domain.entities.PersonaInsumo;
import com.serviceback.serviceback.domain.entities.fkClass.PersonaInsumoPK;

@Repository
public interface PersonaInsumoRepository extends JpaRepository<PersonaInsumo, PersonaInsumoPK> {

    @Query("SELECT pi.insumo FROM PersonaInsumo pi WHERE pi.nrodoc.nrodoc = :nrodoc")
    List<Insumo> findInsumosByProveedor(@Param("nrodoc") String nrodoc);

}
