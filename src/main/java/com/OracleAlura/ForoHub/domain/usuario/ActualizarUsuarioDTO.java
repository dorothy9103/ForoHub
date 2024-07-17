package com.OracleAlura.ForoHub.domain.usuario;

public record ActualizarUsuarioDTO(
        String password,
        Role role,
        String nombre,
        String apellido,
        String email,
        Boolean enabled

) {
}
