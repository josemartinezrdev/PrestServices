package com.serviceback.serviceback.application.services;

import java.util.List;

import java.util.Optional;

import com.serviceback.serviceback.domain.dtos.UserDto;
import com.serviceback.serviceback.domain.entities.security.User;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

public interface IUser {

    User registerOneCustomer(UserDto newUser);

    Optional<User> findOneByUsername(String nombre);

    List<User> findAll();

    User findById(String id) throws GlobalExceptions;

    User save(User user) throws GlobalExceptions;

    User update(String id, User user);

    User delete(String id);
}
