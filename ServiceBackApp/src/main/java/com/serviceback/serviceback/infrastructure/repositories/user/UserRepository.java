package com.serviceback.serviceback.infrastructure.repositories.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceback.serviceback.domain.entities.security.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findOneByUsername(String nombre);
}
