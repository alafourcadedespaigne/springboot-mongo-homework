package com.alafourcadev.demomongodb.web.controllers.person;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.services.CreatePersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreatePersonRestController.class)
class CreatePersonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePersonService personService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void crear() throws Exception {
        // Given
        Person person = Person.builder().name("Jhon").age(35).build();
        when(personService.save(any())).then(invocation ->{
            Person p = invocation.getArgument(0);
            p.setAge(35);
            return p;
        });

        // when
        mockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("result.name", is("Jhon")))
                .andExpect(jsonPath("result.age", is(35)));
        verify(personService).save(any());
    }
}