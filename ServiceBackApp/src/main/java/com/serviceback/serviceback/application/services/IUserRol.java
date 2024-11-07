package com.serviceback.serviceback.application.services;

import java.util.List;

import com.serviceback.serviceback.domain.entities.UserRol;
import com.serviceback.serviceback.domain.entities.fkClass.UserRolPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IUserRol {
    List<UserRol> findAll();

    UserRol findById(UserRolPK id) throws GlobalExceptions;

    UserRol save(UserRol userrol) throws GlobalExceptions;

    UserRol update(UserRolPK id, UserRol userrol);

    UserRol delete(UserRolPK id);
}
