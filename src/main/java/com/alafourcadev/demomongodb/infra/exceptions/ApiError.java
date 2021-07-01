package com.alafourcadev.demomongodb.infra.exceptions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@Data
public class ApiError {

    private int status;
    private HttpStatus error;
    private String timestamp;
    private String message;
    private String debugMessage;
    private DomainErrorDetails errorDetails;

    private ApiError() {
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
    }

    public ApiError(HttpStatus status) {
        this();
        this.error = status;
        this.status = status.value();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.error = status;
        this.status = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public void loadErrorDetails(DomainErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
        if (errorDetails != null) {
            this.message = errorDetails.getMessage();
            this.debugMessage = errorDetails.getMessage();
        }
    }

    public void loadErrorDetails(FieldError fieldError) {
        DomainErrorDetails domainErrorDetails = new DomainErrorDetails(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage()
        );
        loadErrorDetails(domainErrorDetails);
    }

    public void loadErrorDetails(ObjectError objectError) {
        DomainErrorDetails errorDomainDetailsTwo = new DomainErrorDetails(
                objectError.getObjectName(),
                null,
                null,
                objectError.getDefaultMessage()
        );
        loadErrorDetails(errorDomainDetailsTwo);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    public void loadErrorDetails(ConstraintViolation<?> cv) {
        DomainErrorDetails errorDomainDetailsThree = new DomainErrorDetails(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage()
        );
        loadErrorDetails(errorDomainDetailsThree);
    }
}

