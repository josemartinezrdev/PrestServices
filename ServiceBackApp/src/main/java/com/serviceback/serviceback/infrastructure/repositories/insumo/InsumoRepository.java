package com.serviceback.serviceback.infrastructure.repositories.insumo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.serviceback.serviceback.domain.entities.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
    boolean existsByNombre(String nombre);

    @Query("SELECT i, si FROM Insumo i JOIN ServicioInsumo si ON si.insumo.id = i.id WHERE si.servicio.id = :idservice")
    List<Insumo> findInsumosByService(@Param("idservice") int idservice);

}
