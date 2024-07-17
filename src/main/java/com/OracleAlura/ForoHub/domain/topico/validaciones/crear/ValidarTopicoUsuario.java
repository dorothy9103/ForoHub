package com.OracleAlura.ForoHub.domain.topico.validaciones.crear;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.OracleAlura.ForoHub.domain.usuario.*;
import com.OracleAlura.ForoHub.domain.topico.*;

@Component
public class ValidarTopicoUsuario implements ValidarTopicoCreado{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(CrearTopicoDTO data) {
        var existeUsuario = repository.existsById(data.usuarioId());
        if(!existeUsuario){
            throw new ValidationException("Este usuario no existe");
        }

        var usuarioHabilitado = repository.findById(data.usuarioId()).get().getEnabled();
        if(!usuarioHabilitado){
            throw new ValidationException("Este usuario fue deshabiliado.");
        }

    }
}
