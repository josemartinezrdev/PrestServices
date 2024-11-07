package com.serviceback.serviceback.infrastructure.repositories.userol;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IUserRol;
import com.serviceback.serviceback.domain.entities.UserRol;
import com.serviceback.serviceback.domain.entities.fkClass.UserRolPK;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class UserRolImp implements IUserRol {
    @Autowired
    private UserRolRepository userrolRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserRol> findAll() {
        List<UserRol> userrols = userrolRepository.findAll();
        if (userrols.isEmpty()) {
            throw new GlobalExceptions("No se encontraron campos.");
        }
        return userrols;
    }

    @Transactional(readOnly = true)
    @Override
    public UserRol findById(UserRolPK id) throws GlobalExceptions {
        return userrolRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas buscar no existe"));
    }

    @Override
    public UserRol save(UserRol userrol) throws GlobalExceptions {
        if (userrol == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }

        return userrolRepository.save(userrol);
    }

    @Override
    public UserRol update(UserRolPK id, UserRol newUserRol) throws GlobalExceptions {
        UserRol userrol = userrolRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas actualizar no existe"));
        userrol.setUser(newUserRol.getUser());
        userrol.setRol(newUserRol.getRol());
        return userrolRepository.save(userrol);
    }

    @Override
    public UserRol delete(UserRolPK id) throws GlobalExceptions {
        UserRol userrol = userrolRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El campo que intentas eliminar no existe"));
        userrolRepository.delete(userrol);
        return userrol;
    }
}
