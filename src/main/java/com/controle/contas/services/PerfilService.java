package com.controle.contas.services;

import com.controle.contas.entities.Perfil;
import com.controle.contas.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PerfilService implements UserDetailsService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Perfil perfil = perfilRepository.findByNome(username).orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
        return User
                .builder()
                .username(perfil.getNome())
                .password(perfil.getSenha())
                .roles("USER")
                .build();
    }
}
