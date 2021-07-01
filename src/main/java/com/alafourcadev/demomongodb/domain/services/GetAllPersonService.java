package com.alafourcadev.demomongodb.domain.services;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * GetAllPersonService It is the component where the business logic associated with obtaining the list of people is implemented
 * Please see the {@link Person}
 *
 * @author Alejandro
 * @since 2021-07-01
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllPersonService {

    private final PersonRepository personRepository;

    /**
     * This method is used to obtain a list of the people that are in the database
     * @return List of people
     */
    public List<Person> getAll() {

        return personRepository.getAll();
    }
}
