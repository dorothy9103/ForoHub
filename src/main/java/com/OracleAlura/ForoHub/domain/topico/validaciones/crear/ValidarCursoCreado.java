package com.OracleAlura.ForoHub.domain.topico.validaciones.crear;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.OracleAlura.ForoHub.domain.curso.*;
import com.OracleAlura.ForoHub.domain.topico.*;

@Component
public class ValidarCursoCreado implements ValidarTopicoCreado{

    @Autowired
    private CursoRepository repository;

    @Override
    public void validate(CrearTopicoDTO data) {
        var ExisteCurso = repository.existsById(data.cursoId());
        if(!ExisteCurso){
            throw new ValidationException("Este curso no existe.");
        }

        var cursoHabilitado = repository.findById(data.cursoId()).get().getActivo();
        if(!cursoHabilitado){
            throw new ValidationException("Este curso no esta disponible en este momento.");
        }
    }
}

