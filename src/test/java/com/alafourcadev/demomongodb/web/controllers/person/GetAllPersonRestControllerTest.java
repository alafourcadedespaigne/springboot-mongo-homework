package com.alafourcadev.demomongodb.web.controllers.person;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.services.GetAllPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetAllPersonRestController.class)
class GetAllPersonRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GetAllPersonService personService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    public Optional<Person> createPerson001() {
        return Optional.of(Person.builder().name("Marta").age(50).build());
    }

    public Optional<Person> createPerson002() {
        return Optional.of(Person.builder().name("Carl").age(60).build());
    }

    @Test
    void getAll() throws Exception {

        //Given
        List<Person> people = Arrays.asList(createPerson001().get(),
                createPerson002().get()
        );
        when(personService.getAll()).thenReturn(people);


        // When
        mvc.perform(get("/people").contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result[0].name").value("Marta"))
                .andExpect(jsonPath("result[0].age").value(50))
                .andExpect(jsonPath("result[1].name").value("Carl"))
                .andExpect(jsonPath("result[1].age").value(60))
                .andExpect(jsonPath("result", hasSize(2)))
                .andExpect(content().json("{'statusCode':200,'statusMessage':'OK','resultCount':2,'result':[{'name':'Marta','age':50},{'name':'Carl','age':60}]}"));
        verify(personService).getAll();
    }
}