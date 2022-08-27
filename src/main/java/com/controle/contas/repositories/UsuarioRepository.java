package com.controle.contas.repositories;

import com.controle.contas.entities.Conta;
import com.controle.contas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //select * from tb_conta where usuario_id = 1
//    @Query("SELECT obj FROM Conta obj WHERE obj.usuario.id = :id_usuario ORDER BY hora")
//    List<Conta> findAllContaByIdUsuario(@Param(value = "id_usuario") Long id_usuario);
}
