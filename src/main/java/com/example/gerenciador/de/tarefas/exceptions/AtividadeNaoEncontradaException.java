package com.example.gerenciador.de.tarefas.exceptions;

public class AtividadeNaoEncontradaException extends RuntimeException{

    public AtividadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
