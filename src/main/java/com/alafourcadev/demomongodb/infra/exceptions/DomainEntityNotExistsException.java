package com.alafourcadev.demomongodb.infra.exceptions;

public final class DomainEntityNotExistsException extends RuntimeException {

    public DomainEntityNotExistsException(Class<?> entityClass, Object attribute) {
        super(String.format("No record found for entity <%s> with value <%s>", entityClass.getSimpleName(), attribute));
    }
}
