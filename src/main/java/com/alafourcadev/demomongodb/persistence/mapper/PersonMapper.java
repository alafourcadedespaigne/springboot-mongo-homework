package com.alafourcadev.demomongodb.persistence.mapper;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.persistence.model.PersonDocument;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPerson(PersonDocument personDocument);

    List<Person> toPeople(List<PersonDocument> personDocumentList);

    @InheritInverseConfiguration
    PersonDocument toPersonDocument(Person person);
}
