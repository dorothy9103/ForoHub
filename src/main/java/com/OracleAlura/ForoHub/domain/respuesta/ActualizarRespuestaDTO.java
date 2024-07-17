package com.OracleAlura.ForoHub.domain.respuesta;

public record ActualizarRespuestaDTO(
        String mensaje,
        Boolean solucion,
        Boolean borrado
    ) {
}
