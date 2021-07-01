package com.alafourcadev.demomongodb.persistence.repositories;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.mapper.PersonMapper;
import com.alafourcadev.demomongodb.domain.repositories.PersonRepository;
import com.alafourcadev.demomongodb.persistence.model.PersonDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * PersonMongoDBRepository is a concrete implementation for the MongoDB data source of the functionalities
 * described in the Person Repository
 * Please see the {@link com.alafourcadev.demomongodb.domain.repositories.PersonRepository}
 *
 * @author Alejandro
 * @version 1.0
 * @since 2021-07-01
 */
@Repository
@RequiredArgsConstructor
public class PersonMongoDBRepository implements PersonRepository {

    private final PersonCrudRepository crudRepository;
    private final PersonMapper mapper;

    /**
     * This method is used to persist a person in the mongodb database, using the PersonMapper
     * class to transform the person object of the domain into its corresponding Person Document
     * Please see the {@link com.alafourcadev.demomongodb.domain.mapper.PersonMapper}
     *
     * @param person Entity of the domain that contains the person data.
     *               See the {@link com.alafourcadev.demomongodb.domain.entities.Person}
     * @return Entity person saved
     */
    @Override
    public Optional<Person> save(Person person) {
        PersonDocument personDocument = mapper.toPersonDocument(person);
        personDocument = crudRepository.save(personDocument);
        return Optional.of(mapper.toPerson(personDocument));
    }

    /**
     * This method is used to query the database to find a person by name, using the PersonMapper
     * class to transform the document object of the database into its corresponding Person of Domain
     * Please see the {@link com.alafourcadev.demomongodb.domain.mapper.PersonMapper}
     *
     * @param name Name of the person to be found in the  database.
     *               See the {@link com.alafourcadev.demomongodb.domain.entities.Person}
     * @return Person who matches the search criteria
     */
    @Override
    public Optional<Person> findByName(String name) {
        Optional<PersonDocument> personDocument = crudRepository.findByName(name);
        return personDocument.map(mapper::toPerson);
    }

    /**
     * This method is used to query the database to find a person by age,using the PersonMapper
     *  class to transform the document object of the database into its corresponding Person of Domain
     * Please see the {@link com.alafourcadev.demomongodb.domain.mapper.PersonMapper}
     *
     * @param age Age of the person to be found in the  database.
     *               See the {@link com.alafourcadev.demomongodb.domain.entities.Person}
     * @return Person who matches the search criteria
     */
    @Override
    public Optional<Person> findByAge(Integer age) {
        Optional<PersonDocument> personDocument = crudRepository.findByAge(age);
        if (personDocument.isPresent())
            return Optional.of(mapper.toPerson(personDocument.get()));
        return Optional.empty();
    }

    /**
     * This method is used to query the database to find a person by age and name, using the PersonMapper
     * class to transform the document object of the database into its corresponding Person of Domain
     * Please see the {@link com.alafourcadev.demomongodb.domain.mapper.PersonMapper}
     *
     * @param name Name of the person to be found in the  database.
     * @param age Age of the person to be found in the  database.
     *               See the {@link com.alafourcadev.demomongodb.domain.entities.Person}
     * @return Person who matches the search criteria
     */
    @Override
    public Optional<Person> findByNameAndAge(String name, Integer age) {
        List<PersonDocument> people = crudRepository.findAll();

        PersonDocument filteredPersonDocument = people.stream()
                .filter(doc -> name.equals(doc.getName()) && age.equals(doc.getAge()))
                .findAny()
                .orElse(null);
        return Optional.of(mapper.toPerson(filteredPersonDocument));
    }

    /**
     * This method is used to return a list of all the people found in the database, using the PersonMapper
     * class to transform the list of document object of the database into its corresponding Person of Domain
     * Please see the {@link com.alafourcadev.demomongodb.domain.mapper.PersonMapper}
     * See the {@link com.alafourcadev.demomongodb.domain.entities.Person}
     * @return Entity person saved
     */
    @Override
    public List<Person> getAll() {
        return mapper.toPeople(crudRepository.findAll());
    }
}
