package com.alura.challenge.forohub.ForoHub.controller;

import com.alura.challenge.forohub.ForoHub.Infra.security.DatosJWTToken;
import com.alura.challenge.forohub.ForoHub.Infra.security.TokenService;
import com.alura.challenge.forohub.ForoHub.domain.Usuarios.DatosAutenticacionUsuario;
import com.alura.challenge.forohub.ForoHub.domain.Usuarios.Usuario;
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

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(),
                datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWToken));
    }
}
