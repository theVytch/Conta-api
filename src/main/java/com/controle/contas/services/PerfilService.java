package com.controle.contas.services;

import com.controle.contas.entities.Perfil;
import com.controle.contas.repositories.PerfilRepository;
import com.controle.contas.services.exceptions.UsuarioCadastradoException;
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

    public Perfil create(Perfil perfil){
        boolean exists = perfilRepository.existsByUsername(perfil.getUsername());
        if ( exists ){
            throw new UsuarioCadastradoException(perfil.getUsername());
        }
        return perfilRepository.save(perfil);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Perfil perfil = perfilRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
        return User
                .builder()
                .username(perfil.getUsername())
                .password(perfil.getPassword())
                .roles("USER")
                .build();
    }
}
