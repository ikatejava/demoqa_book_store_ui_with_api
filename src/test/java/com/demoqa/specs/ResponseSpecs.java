package com.demoqa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification successfulRegistrationResponseSpec201 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody("userID", notNullValue())
            .expectBody("username", notNullValue())
            .expectBody("books", notNullValue())
            .build();

    public static ResponseSpecification successfulAuthorizationResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("userId", notNullValue())
            .expectBody("username", notNullValue())
            .expectBody("password", notNullValue())
            .expectBody("token", notNullValue())
            .expectBody("expires", notNullValue())
            .expectBody("created_date", notNullValue())
            .expectBody("isActive", notNullValue())
            .build();

    public static ResponseSpecification successfulDeletionResponseSpec204 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification unauthorizedAccountFailedTestsResponseSpec401 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(401)
            .expectBody("code", notNullValue())
            .expectBody("message", notNullValue())
            .build();

    public static ResponseSpecification getUserInfoSuccessfulResponseSpec200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("userId", notNullValue())
            .expectBody("username", notNullValue())
            .expectBody("books", notNullValue())
            .build();

    public static ResponseSpecification NonExistentISBNResponse400 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .expectBody("code", notNullValue())
            .expectBody("message", notNullValue())
            .build();
}
