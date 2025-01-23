package com.jgroup.DesafioMeta.service.exception;

public class ClienteNaoEncontradoException extends RuntimeException{

    public ClienteNaoEncontradoException(String mensagem) {
        super (mensagem);
    }
}
