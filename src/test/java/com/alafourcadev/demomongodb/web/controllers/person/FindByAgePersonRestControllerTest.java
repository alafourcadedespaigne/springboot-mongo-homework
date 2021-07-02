package com.alafourcadev.demomongodb.web.controllers.person;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.services.FilterPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FindByAgePersonRestController.class)
class FindByAgePersonRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FilterPersonService filterPersonService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    public Optional<Person> createPerson001() {
        return Optional.of(Person.builder().name("Marta").age(50).build());
    }

    @Test
    void findByAge() throws Exception {

        // Given
        when(filterPersonService.findByAge(34)).thenReturn(createPerson001().get());

        // When
        mvc.perform(get("/people/age/34").contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result.name").value("Marta"))
                .andExpect(jsonPath("result.age").value(50));

        verify(filterPersonService).findByAge(34);
    }
}