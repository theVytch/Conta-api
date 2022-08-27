package com.controle.contas.resources;

import com.controle.contas.entities.Perfil;
import com.controle.contas.repositories.PerfilRepository;
import com.controle.contas.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilResource {

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Perfil obj){
        perfilRepository.save(obj);
    }
}
