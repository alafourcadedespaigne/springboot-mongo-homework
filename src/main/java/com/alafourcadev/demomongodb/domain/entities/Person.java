package com.alafourcadev.demomongodb.domain.entities;

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
public class Person {

    private String name;
    private Integer age;
}
