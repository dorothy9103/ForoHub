package com.OracleAlura.ForoHub.domain.usuario.validaciones.actualizar;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.OracleAlura.ForoHub.domain.usuario.*;
import com.OracleAlura.ForoHub.domain.usuario.validaciones.actualizar.ValidarActualizarUsuario;
@Component
public class ValidaActualizacionUsuario implements ValidarActualizarUsuario {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(ActualizarUsuarioDTO data) {
        if(data.email() != null){
            var emailDuplicado = repository.findByEmail(data.email());
            if(emailDuplicado != null){
                throw new ValidationException("Este email ya esta en uso");
            }
        }
    }
}