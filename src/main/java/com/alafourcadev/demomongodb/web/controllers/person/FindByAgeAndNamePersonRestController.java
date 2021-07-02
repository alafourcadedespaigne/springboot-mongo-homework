package com.alafourcadev.demomongodb.web.controllers.person;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.services.FilterPersonService;
import com.alafourcadev.demomongodb.infra.common.ApiResponseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "Person Rest Controller", value = "REST controller to search for people by age and name")
@RequestMapping("/people")
public class FindByAgeAndNamePersonRestController {

    public static final String OPERATION = "Find person by name and age";

    public final FilterPersonService service;

    @GetMapping("/fullsearch")
    @ApiOperation(OPERATION)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "201", description = "Created.", content = @Content(schema = @Schema(implementation = FindByNameAndAgePersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<FindByNameAndAgePersonResponse> findByAge(@RequestParam("age")
                                                                    @ApiParam(value = "Age of the person to be searched",
                                                                            required = true) Integer age, @RequestParam("name")
                                                                    @ApiParam(value = "Name of the person to be searched",
                                                                            required = true) String name) {
        Person person = service.findByNameAndAge(name,age);
        return new ResponseEntity<>(new FindByNameAndAgePersonResponse(person), HttpStatus.OK);
    }

    public class FindByNameAndAgePersonResponse extends ApiResponseBase<Person> {

        public FindByNameAndAgePersonResponse(Person result) {
            super(result, HttpStatus.OK, 1);
        }
    }


}
