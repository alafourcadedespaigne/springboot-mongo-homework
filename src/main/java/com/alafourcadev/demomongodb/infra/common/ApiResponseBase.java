package com.alafourcadev.demomongodb.infra.common;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * Base class that groups the information to be returned in all the http responses of the api.
 * This class is parameterized with an object to be returned in JSON format
 * @param <T> Domain object to return
*
 */
public class ApiResponseBase<T> {

    @ApiModelProperty(position = 1, required = true, value = "Operation status code (http status code)")
    // @JsonProperty("status")
    private Integer statusCode;

    @ApiModelProperty(position = 2, required = true, value = "Detail of the status of the operation")
    // @JsonProperty("message")
    private String statusMessage;

    @ApiModelProperty(position = 3, value = "Number of records returned or affected by the operation\n")
    // @JsonProperty("count")
    private Integer resultCount;

    @ApiModelProperty(position = 4, value = "Result of the operation")
    private T result;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        if (statusCode.equals(200)) {
            this.statusMessage = "OK";
        }
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ApiResponseBase() {
        this.statusCode = 200;
        this.statusMessage = "";
        this.resultCount = 0;
    }

    public ApiResponseBase(T result) {
        this.statusCode = 200;
        this.statusMessage = "";
        this.resultCount = 1;
        this.result = result;
    }

    public ApiResponseBase(T result, HttpStatus httpStatus, Integer resultCount) {
        this.statusCode = httpStatus.value();
        this.statusMessage = "Operación correcta.";
        this.resultCount = resultCount;
        this.result = result;
    }

    public ApiResponseBase(T result, Integer resultCount) {
        this.statusCode = 200;
        this.statusMessage = "";
        this.resultCount = resultCount;
        this.result = result;
    }

    //en caso de error entregar codigo de fallo y mensaje
    public ApiResponseBase(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.resultCount = 0;
    }

}
