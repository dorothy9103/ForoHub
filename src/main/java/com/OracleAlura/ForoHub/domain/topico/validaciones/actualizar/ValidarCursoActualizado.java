package com.OracleAlura.ForoHub.domain.topico.validaciones.actualizar;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.OracleAlura.ForoHub.domain.curso.*;

@Component
public class ValidarCursoActualizado implements ValidarTopicoActualizado {

    @Autowired
    private CursoRepository repository;

    @Override
    public void validate(com.OracleAlura.ForoHub.domain.topico.ActualizarTopicoDTO data) {
        if(data.cursoId() != null){
            var ExisteCurso = repository.existsById(data.cursoId());
            if (!ExisteCurso){
                throw new ValidationException("Este curso no existe");
            }

            var cursoHabilitado = repository.findById(data.cursoId()).get().getActivo();
            if(!cursoHabilitado){
                throw new ValidationException("Este curso no esta disponible en este momento.");
            }
        }

    }
}
