package com.alafourcadev.demomongodb.infra.exceptions;

import lombok.Getter;

@SuppressWarnings({"serial"})

@Getter
public class DomainException extends RuntimeException {

    private Object[] messageParams;
    private DomainErrorDetails errorDetails;



    public DomainException(Object[] messageParams) {
        super("");
        this.messageParams = messageParams;
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(DomainErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        this.errorDetails = errorDetails;
    }
}