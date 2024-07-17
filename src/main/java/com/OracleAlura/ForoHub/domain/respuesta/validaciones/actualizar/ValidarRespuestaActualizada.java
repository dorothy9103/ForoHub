package com.OracleAlura.ForoHub.domain.respuesta.validaciones.actualizar;

import com.OracleAlura.ForoHub.domain.respuesta.ActualizarRespuestaDTO;

public interface ValidarRespuestaActualizada {

    public void validate(ActualizarRespuestaDTO data, Long respuestaId);

}

