package com.controle.contas.services;

import com.controle.contas.dto.ContaDto;
import com.controle.contas.entities.Conta;
import com.controle.contas.entities.Usuario;
import com.controle.contas.repositories.ContaRepository;
import com.controle.contas.services.exceptions.DatabaseException;
import com.controle.contas.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private UsuarioService usuarioService;


    public List<Conta> findAll(){
        return contaRepository.findAll();
    }

    public Conta findById(Long id){
        Optional<Conta> obj = contaRepository.findById(id);
        return obj.orElseThrow(() ->  new IllegalStateException(id+" não encontrado"));
    }

//    public List<Conta> findAllByIdUsuario(Long id){
//        return contaRepository.findAllContaByIdUsuario(id);
//    }

    public Conta save(ContaDto obj){
        Conta conta = contaDtoToConta(obj);
        return contaRepository.save(conta);
    }

    public Conta update(Long id, Conta newObj) {
        try {
            Conta entity = contaRepository.getReferenceById(id);
            updateData(entity, newObj);
            return contaRepository.save(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            contaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Conta entity, Conta newObj) {
        entity.setHora(LocalDate.now());
        entity.setCompra(newObj.getCompra());
        entity.setDescricao(newObj.getDescricao());
        entity.setValor(newObj.getValor());
    }

    public Conta contaDtoToConta(ContaDto objDto) {
        Usuario usuario = usuarioService.findById(objDto.getUsuario_id());
        return new Conta(objDto.getCompra(), objDto.getDescricao(), objDto.getValor(), usuario);
    }

}
