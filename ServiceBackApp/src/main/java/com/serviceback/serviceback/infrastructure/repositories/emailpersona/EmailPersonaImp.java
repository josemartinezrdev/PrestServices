package com.serviceback.serviceback.infrastructure.repositories.emailpersona;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IEmailPersona;
import com.serviceback.serviceback.domain.entities.EmailPersona;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class EmailPersonaImp implements IEmailPersona {
    @Autowired
    private EmailPersonaRepository emailpersonaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EmailPersona> findAll() {
        List<EmailPersona> emailpersonas = emailpersonaRepository.findAll();
        if (emailpersonas.isEmpty()) {
            throw new GlobalExceptions("No se encontraron emails de personas.");
        }
        return emailpersonas;
    }

    @Transactional(readOnly = true)
    @Override
    public EmailPersona findById(int id) throws GlobalExceptions {
        return emailpersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El email de la persona que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public EmailPersona save(EmailPersona emailpersona) throws GlobalExceptions {
        if (emailpersona == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        if (emailpersonaRepository.existsByEmail(emailpersona.getEmail())) {
            throw new GlobalExceptions("El valor ya existe");
        }
        return emailpersonaRepository.save(emailpersona);
    }

    @Transactional
    @Override
    public EmailPersona update(int id, EmailPersona newEmailPersona) throws GlobalExceptions {
        EmailPersona emailpersona = emailpersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El email de la persona que intentas actualizar no existe"));
        emailpersona.setEmail(newEmailPersona.getEmail());
        emailpersona.setTipoEmail(newEmailPersona.getTipoEmail());
        emailpersona.setPersona(newEmailPersona.getPersona());
        return emailpersonaRepository.save(emailpersona);
    }

    @Transactional
    @Override
    public EmailPersona delete(int id) throws GlobalExceptions {
        EmailPersona emailpersona = emailpersonaRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El email de la persona que intentas eliminar no existe"));
        emailpersonaRepository.delete(emailpersona);
        return emailpersona;
    }
}
