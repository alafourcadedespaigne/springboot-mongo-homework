package com.alafourcadev.demomongodb.infra.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@SuppressWarnings({"serial"})

@Getter
public class DomainException extends RuntimeException {

    private Object[] messageParams;
    private DomainErrorDetails errorDetails;
    private HttpStatus status;


    public DomainException(HttpStatus status) {
        super("");
        this.status = status;
    }

    public DomainException(HttpStatus  status, Object[] messageParams) {
        super("");
        this.status = status;
        this.messageParams = messageParams;
    }

    public DomainException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public DomainException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public DomainException(DomainErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        this.errorDetails = errorDetails;
    }
}