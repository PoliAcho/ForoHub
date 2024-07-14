package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.usuario.IUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CAutentServ implements UserDetailsService {

  @Autowired
  private IUsuarioRepo usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository.findByLogin(username);
  }
}