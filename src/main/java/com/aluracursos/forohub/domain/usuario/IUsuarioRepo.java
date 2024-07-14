package com.aluracursos.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepo extends JpaRepository<CUsuario, Long> {
  UserDetails findByLogin(String username);
}
