package com.alafourcadev.demomongodb.persistence.repositories;

import com.alafourcadev.demomongodb.persistence.model.PersonDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * PersonCrudRepository describes the basic functionalities of access to data of the Person Document
 *
 * Please see the {@link org.springframework.data.mongodb.repository.MongoRepository} class for true identity
 * @author  Alejandro
 * @version 1.0
 * @since   2021-07-01
 *
 */
public interface PersonCrudRepository extends MongoRepository<PersonDocument, UUID> {

    Optional<PersonDocument> findByName(String name);
    Optional<PersonDocument> findByAge(Integer age);

}
