package com.alafourcadev.demomongodb.web.controllers.person;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.services.CreatePersonService;
import com.alafourcadev.demomongodb.infra.common.ApiResponseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Person Rest Controller", value = "Controlador para calculo de tramites")
@RequestMapping("/people")
public class CreatePersonRestController {

    public static final String OPERATION = "Create a person in the system";

    public final CreatePersonService service;

    @PostMapping
    @ApiOperation(OPERATION)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "201", description = "Created.", content = @Content(schema = @Schema(implementation = CreatePersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<CreatePersonResponse> crear(@RequestBody Person person) {
        Person personResponse = service.save(person);
        return new ResponseEntity<>(new CreatePersonResponse(personResponse), HttpStatus.CREATED);
    }

    public class CreatePersonResponse extends ApiResponseBase<Person> {

        public CreatePersonResponse(Person result) {
            super(result, HttpStatus.CREATED, 1);
        }
    }





}
