package com.controle.contas.services.exceptions;

public class UsuarioCadastradoException extends RuntimeException{

    public UsuarioCadastradoException(String login){
        super("Usuário ja cadastrado para o login: " + login);
    }
}
