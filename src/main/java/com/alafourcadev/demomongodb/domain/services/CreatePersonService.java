package com.alafourcadev.demomongodb.domain.services;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CreatePersonService It is the component where the business logic associated with the creation of the Person object is implemented
 * Please see the {@link com.alafourcadev.demomongodb.domain.entities.Person}
 * @since   2021-07-01
 * @author Alejandro
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePersonService {

    private final PersonRepository personRepository;

    /**
     * This method is used to create a person object
     * @param newPerson  Person entity to persist
     * @return {@link com.alafourcadev.demomongodb.domain.entities.Person} type Concrete Object saved in the DB
     */
    public Person save(Person newPerson) {

        Optional<Person> optionalPerson = personRepository.save(newPerson);
        if (optionalPerson.isPresent())
            return optionalPerson.get();
        else
            //TODO created custom exc
            return null;
    }
}
