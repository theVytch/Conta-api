package com.controle.contas.resources;

import com.controle.contas.entities.Conta;
import com.controle.contas.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaResource {

    @Autowired
    private ContaService contaService;

    // https://www.codejava.net/frameworks/spring-boot/export-data-to-excel-example excel export

    @GetMapping
    public ResponseEntity<List<Conta>> findAll(){
        List<Conta> list = contaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> findById(@PathVariable Long id){
        Conta obj = contaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Conta> save(@RequestBody Conta obj){
        Conta conta = contaService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(conta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody Conta obj){
        obj = contaService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
