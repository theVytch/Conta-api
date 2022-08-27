package com.controle.contas.services;

import com.controle.contas.entities.Usuario;
import com.controle.contas.repositories.UsuarioRepository;
import com.controle.contas.services.exceptions.DatabaseException;
import com.controle.contas.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() ->  new IllegalStateException(id+" não encontrado"));
    }

    public Usuario save(Usuario obj){
        return usuarioRepository.save(obj);
    }

    public Usuario update(Long id, Usuario newObj) {
        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            updateData(entity, newObj);
            return usuarioRepository.save(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Usuario entity, Usuario newObj) {
        entity.setNome(newObj.getNome());
        entity.setEmail(newObj.getEmail());
        entity.setSenha(newObj.getSenha());
    }
}
