package br.com.addr.exceptions;

public class InvalidCepException extends RuntimeException{

    public InvalidCepException(String message) {
        super(message);
    }
}
