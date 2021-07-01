package com.alafourcadev.demomongodb.infra.exceptions;

public final class DomainEntityNotExistsException extends RuntimeException {

    public DomainEntityNotExistsException(Class<?> entityClass, Object entityId) {
        super(String.format("No record found for entity <%s> with id <%s>", entityClass.getSimpleName(), entityId));
    }
}
