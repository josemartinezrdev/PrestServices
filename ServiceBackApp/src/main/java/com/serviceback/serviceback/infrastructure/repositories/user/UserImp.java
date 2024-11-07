package com.serviceback.serviceback.infrastructure.repositories.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.serviceback.serviceback.application.services.IRol;
import com.serviceback.serviceback.application.services.IUser;
import com.serviceback.serviceback.domain.dtos.UserDto;
import com.serviceback.serviceback.domain.entities.security.Rol;
import com.serviceback.serviceback.domain.entities.security.User;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class UserImp implements IUser {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRol rolService;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new GlobalExceptions("No se encontraron usuarios.");
        }
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(String id) throws GlobalExceptions {
        return userRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El usuario que intentas buscar no existe"));
    }

    @Override
    public User save(User user) throws GlobalExceptions {
        if (user == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User newUser) throws GlobalExceptions {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El usuario que intentas actualizar no existe"));
        user.setNombre(newUser.getNombre());
        user.setPassword(newUser.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User delete(String id) throws GlobalExceptions {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El usuario que intentas eliminar no existe"));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User registerOneCustomer(UserDto newUser) {
        validatePassword(newUser);

        User user = new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setNombre(newUser.getNombre());
        user.setUsername(newUser.getUsername());
        Rol rol = rolService.findByNombre(newUser.getRol());
        user.setRole(rol);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String nombre) {
        return userRepository.findOneByUsername(nombre);
    }

    private void validatePassword(UserDto dto) {
        if (!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())) {
            throw new GlobalExceptions("Error validando la contraseña");
        }
        if (!dto.getPassword().equals(dto.getRepeatedPassword())) {
            throw new GlobalExceptions("Las contraseñas no coinciden");
        }
    }

}
