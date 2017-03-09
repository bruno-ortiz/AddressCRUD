package br.com.addr.exceptions;

public class AddressException extends RuntimeException {
    public AddressException(String message) {
        super(message);
    }

    public AddressException(String message, Throwable t) {
        super(message, t);
    }
}
