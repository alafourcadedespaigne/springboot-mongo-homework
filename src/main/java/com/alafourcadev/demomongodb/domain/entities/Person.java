package com.alafourcadev.demomongodb.domain.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * Person is the main entity that we will use to represent the person who is the object of our business.
 *
 * @author Alejandro
 *
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(description = "Domain Model corresponding to Person")
public class Person {

    @ApiModelProperty(value = "Person name", required = true)
    private String name;
    @ApiModelProperty(value = "Person age", required = true)
    private Integer age;
}
