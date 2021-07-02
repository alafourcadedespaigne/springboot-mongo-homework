package com.alafourcadev.demomongodb.domain.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


/**
 * Person is the main entity that we will use to represent the person who is the object of our business.
 *
 * @author Alejandro
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(description = "Domain Model corresponding to Person")
public class Person {

    @ApiModelProperty(value = "Person name", required = true)

    @Size(min = 3, message = "The name must contain at least 3 characters")
    private String name;
    @ApiModelProperty(value = "Person age", required = true)
    @Min(18)
    @Max(99)
    private Integer age;
}
