package com.alafourcadev.demomongodb.domain.services;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.repositories.PersonRepository;
import com.alafourcadev.demomongodb.infra.exceptions.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * FilterPersonService It is the component where the business logic associated with the search for people filtered by their attributes is implemented
 * Please see the {@link Person}
 *
 * @author Alejandro
 * @since 2021-07-01
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class FilterPersonService {

    private final PersonRepository personRepository;


    /**
     * This method is used to obtain a person from its name attribute
     *
     * @param name Name of the person being searched
     * @return Person matching the search criteria
     */
    public Person findByName(String name) {
        Optional<Person> optionalPerson = personRepository.findByName(name);
        if (optionalPerson.isPresent())
            return optionalPerson.get();
        else
            throw new DomainException(HttpStatus.NOT_FOUND, String.format("Entity %s not found with name %s", "Person", name));
    }

    /**
     * This method is used to obtain a person from its age attribute
     *
     * @param age Age of the person being searched
     * @return Person matching the search criteria
     */
    public Person findByAge(Integer age) {
        Optional<Person> optionalPerson = personRepository.findByAge(age);
        if (optionalPerson.isPresent())
            return optionalPerson.get();
        else
            throw new DomainException(HttpStatus.NOT_FOUND, String.format("Entity %s not found with age %s", "Person", age));

    }

    /**
     * This method is used to obtain a person from its name and age attributes
     *
     * @param name Name of the person being searched
     * @param age  Age of the person being searched
     * @return Person matching the search criteria
     */
    public Person findByNameAndAge(String name, Integer age) {
        Optional<Person> optionalPerson = personRepository.findByNameAndAge(name, age);
        if (optionalPerson.isPresent())
            return optionalPerson.get();
        else
            throw new DomainException(HttpStatus.NOT_FOUND,
                    String.format("Entity Person not found with specified parameters %s and %s", name, age));
    }
}
