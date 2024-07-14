package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.RAutentUsuario;
import com.aluracursos.forohub.domain.usuario.CUsuario;
import com.aluracursos.forohub.infra.security.DatosJWTToken;
import com.aluracursos.forohub.infra.security.CTokenServ;
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
public class CAutentCtrl {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CTokenServ tokenService;

  @PostMapping
  public ResponseEntity autenticarUsuario(@RequestBody @Valid RAutentUsuario datosAutenticacionUsuario) {
    Authentication authToken = new UsernamePasswordAuthenticationToken
       (datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
    var usuarioAutenticado = authenticationManager.authenticate(authToken);
    var JWTtoken = tokenService.generarToken((CUsuario) usuarioAutenticado.getPrincipal());
    return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
  }
}