package com.alafourcadev.demomongodb.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Model representing a MongoDB Document. The objective with this model
 * is to describe the structure of a Person Document within the data source
 * Please @see <a href="https://docs.mongodb.com/manual/core/document/">https://docs.mongodb.com/manual/core/document/</a>
 * @since   2021-07-01
 * @author Alejandro
 */
@Getter
@Setter
@Document(collection = "people")
public class PersonDocument {

    @Id
    private UUID id=UUID.randomUUID();
    private String name;
    private Integer age;

    public PersonDocument() {
    }

}
