package com.controle.contas.resources;

import com.controle.contas.entities.Perfil;
import com.controle.contas.services.PerfilService;
import com.controle.contas.services.exceptions.UsuarioCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/perfil")
public class PerfilResource {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Perfil obj){
        try {
            perfilService.create(obj);
        } catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
