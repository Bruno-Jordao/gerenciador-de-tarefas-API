package com.example.gerenciador.de.tarefas.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException (String mensagem){
        super(mensagem);
    }
}
