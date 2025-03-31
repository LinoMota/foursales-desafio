package com.foursales.desafio.domain.exceptions;

public class OtherUserTriedToPayTheOrderException extends RuntimeException {
    public OtherUserTriedToPayTheOrderException() {
        super("The payment should be payed by the user that did create the order");
    }
}
