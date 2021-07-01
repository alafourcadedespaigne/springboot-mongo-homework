package com.alafourcadev.demomongodb.domain.repositories;


import com.alafourcadev.demomongodb.domain.entities.Person;

import java.util.List;
import java.util.Optional;

/**
 * This is the main interface of the repository of the person object
 * which describes the functionalities that will be used to access the data.
 * <p>
 * <b>Note:</b> All classes linked to people data sources will implement this interface.
 *
 * @author  Alejandro
 * @version 1.0
 * @since   2021-07-01
 */
public interface PersonRepository {

    Optional<Person> save(Person person);

    Optional<Person> findByName(String name);

    Optional<Person> findByAge(Integer age);

    Optional<Person> findByNameAndAge(String name , Integer age);

    List<Person> getAll();


}
