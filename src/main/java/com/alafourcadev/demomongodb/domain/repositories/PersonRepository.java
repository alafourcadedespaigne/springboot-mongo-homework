package com.alafourcadev.demomongodb.domain.repositories;


import com.alafourcadev.demomongodb.domain.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    Optional<Person> save(Person person);

    Optional<Person> findByName(String name);

    Optional<Person> findByAge(Integer age);

    Optional<Person> findByNameAndAge(String name , Integer age);

    List<Person> getAll();


}
