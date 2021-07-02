package com.alafourcadev.demomongodb.infra.config.apidocs;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String MSG_OK_200 = "Successful operation.";
    public static final String MSG_ERROR_500 = "Service not available.";
    public static final String MSG_ERROR_404 = "Resource not found.";


    public static final Contact DEFAULT_CONTACT = new Contact(
            "Alejandro Lafourcade", "https://github.com/alafourcadedespaigne", "alafourcadev@gmail.com");





    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList("application/json",
                    "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alafourcadev.demomongodb.web.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET,getCustomizedResponseMessages())
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
            .title( "API Demo for Nisum")
            .termsOfServiceUrl("urn:tos")
            .version("1.0")
            .description( "Nisum Microservice documentation for people management")
            .contact(DEFAULT_CONTACT)
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .build();


    private List<Response> getCustomizedResponseMessages(){
        List<Response> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseBuilder().code("200").description(MSG_OK_200).build());
        responseMessages.add(new ResponseBuilder().code("404").description(MSG_ERROR_404).build());
        responseMessages.add(new ResponseBuilder().code("500").description(MSG_ERROR_500).build());
        return responseMessages;
    }

}