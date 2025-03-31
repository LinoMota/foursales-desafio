package com.foursales.desafio.domain.exceptions;

public class CantPayInvalidException extends RuntimeException {
    public CantPayInvalidException() {
        super("You cannot pay a order while the order has been cancelled or has been paid");
    }
}
