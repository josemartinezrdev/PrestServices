package com.serviceback.serviceback.infrastructure.repositories.userol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.UserRol;
import com.serviceback.serviceback.domain.entities.fkClass.UserRolPK;

@Repository
public interface UserRolRepository extends JpaRepository<UserRol, UserRolPK> {

}
