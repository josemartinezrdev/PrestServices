package com.serviceback.serviceback.infrastructure.repositories.operation;

import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.security.Operation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o WHERE o.permitAll = true")
    List<Operation> findByPubliccAcces();
}
