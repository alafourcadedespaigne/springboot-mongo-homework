package com.alafourcadev.demomongodb.infra.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = "Parameter " + ex.getParameterName() + " is absent";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }


    /**
     * Handle MethodArgumentNotValidException. Fires when an object does not meet a @Valid validation.
     *
     * @param ex      The MethodArgumentNotValidException is fired when @Valid fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String message = "Validation Error";
        String errorCode = null;
        for (FieldError field : ex.getBindingResult().getFieldErrors()) {
            try {
                Object[] objects = new Object[]{ex.getBindingResult().getTarget()};
                message = messageSource.getMessage(field.getDefaultMessage(), objects, LocaleContextHolder.getLocale());
                errorCode = field.getDefaultMessage();
                break;
            } catch (NoSuchMessageException mex) {
                //Do nothing
            }
        }
        if (ex.getBindingResult().getFieldErrors() != null && !ex.getBindingResult().getFieldErrors().isEmpty())
            apiError.loadErrorDetails(ex.getBindingResult().getFieldErrors().get(0));
        else if (ex.getBindingResult().getGlobalErrors() != null && !ex.getBindingResult().getGlobalErrors().isEmpty())
            apiError.loadErrorDetails(ex.getBindingResult().getGlobalErrors().get(0));
        apiError.setMessage(message);
        return buildResponseEntity(apiError);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Fires when @Valid fails.
     *
     * @param ex ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String message = "Validation Error";
        String errorCode = null;
        for (ConstraintViolation cv : ex.getConstraintViolations()) {
            try {
                Object[] objects = new Object[]{cv.getInvalidValue()};
                message = messageSource.getMessage(cv.getMessage(), objects, LocaleContextHolder.getLocale());
                errorCode = cv.getMessage();
                break;
            } catch (NoSuchMessageException mex) {
                //Do nothing
            }
        }
        if (ex.getConstraintViolations() != null && !ex.getConstraintViolations().isEmpty())
            apiError.loadErrorDetails(new ArrayList<>(ex.getConstraintViolations()).get(0));
        apiError.setMessage(message);
        return buildResponseEntity(apiError);
    }


    /**
     * Handle NoHandlerFoundException. Fires when the method is not found.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("Method not found %s for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    /**
     * MissingServletRequestPartException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestPartException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Parameter " + ex.getRequestPartName() + " not found";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getError());
    }
}
