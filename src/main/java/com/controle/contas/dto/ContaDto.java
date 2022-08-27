package com.controle.contas.dto;

import com.controle.contas.entities.Conta;
import java.io.Serializable;
import java.time.LocalDate;

public class ContaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate hora;
    private String compra;
    private String descricao;
    private Double valor;

    private Long usuario_id;

    public ContaDto(){}

    public ContaDto(Conta obj) {
        this.id = obj.getId();
        this.hora = obj.getHora();
        this.compra = obj.getCompra();
        this.descricao = obj.getDescricao();
        this.valor = obj.getValor();
        this.usuario_id = obj.getUsuario().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getHora() {
        return hora;
    }

    public void setHora(LocalDate hora) {
        this.hora = hora;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}
