package com.OracleAlura.ForoHub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OracleAlura.ForoHub.infra.security.*;
import com.OracleAlura.ForoHub.domain.usuario.*;
import com.OracleAlura.ForoHub.infra.security.TokenService;


@RestController
@RequestMapping("/login")
//@Tag(name = "Autenticación", description = "Obtiene el token del usuario designado para el acceso.")

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionUsuarioDTO.username(),
                autenticacionUsuarioDTO.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDTO(JWTtoken));
    }
}
