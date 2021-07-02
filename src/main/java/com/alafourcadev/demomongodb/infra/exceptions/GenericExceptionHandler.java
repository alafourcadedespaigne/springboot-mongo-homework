package com.alafourcadev.demomongodb.infra.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public GenericExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Generic Exception Handler
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> Method(Throwable ex) {

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        String message = ex.getMessage() == null ? ex.toString() : ex.getMessage();

        Optional<DomainException> domainExceptionOpt = getDomainExceptionFromThrowable(ex);
        if (domainExceptionOpt.isPresent()) {
            DomainException domainException = domainExceptionOpt.get();
            if(domainException.getStatus() != null){
                apiError.setStatus(domainException.getStatus().value());
                apiError.setError(domainException.getStatus());
                apiError.loadErrorDetails(domainException.getErrorDetails());
            }
            else{
                apiError.setStatus(HttpStatus.BAD_REQUEST.value());
                apiError.setError(HttpStatus.BAD_REQUEST);
                apiError.loadErrorDetails(domainException.getErrorDetails());
            }


            try {
                message = domainException.getMessage();
            } catch (NoSuchMessageException mex) {
                message = domainException.getMessage() != null && !domainException.getMessage().isEmpty() ? domainException.getMessage() : mex.getMessage();
            }
        }

        apiError.setMessage(message);
        apiError.setDebugMessage(message);
        log.error(message, ex);
        return buildResponseEntity(apiError);
    }

    private Optional<DomainException> getDomainExceptionFromThrowable(Throwable ex) {
        DomainException domainException = null;
        if (ex instanceof DomainException) {
            domainException = (DomainException) ex;
        } else {
            Throwable cause = ex.getCause();
            while (cause != null && domainException == null) {
                if (cause instanceof DomainException) {
                    domainException = (DomainException) cause;
                }
                cause = cause.getCause();
            }
        }
        return Optional.ofNullable(domainException);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getError());
    }
}
