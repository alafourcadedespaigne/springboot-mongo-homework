package com.alafourcadev.demomongodb.web.controllers.person;

import com.alafourcadev.demomongodb.domain.entities.Person;
import com.alafourcadev.demomongodb.domain.services.GetAllPersonService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Person Rest Controller", value = "REST controller in charge of creating person objects")
@RequestMapping("/people")
public class GetAllPersonRestController {

    public static final String OPERATION = "List all people";

    public final GetAllPersonService service;

    @GetMapping
    @ApiOperation(OPERATION)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "201", description = "Created.", content = @Content(schema = @Schema(implementation = GetAllPersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<GetAllPersonResponse> getAll() {
        List<Person> personResponseList = service.getAll();
        return new ResponseEntity<>(new GetAllPersonResponse(personResponseList), HttpStatus.OK);
    }

    public class GetAllPersonResponse extends ApiResponseBase<List<Person>> {

        public GetAllPersonResponse(List<Person> result) {
            super(result, HttpStatus.OK, result.size());
        }
    }





}
