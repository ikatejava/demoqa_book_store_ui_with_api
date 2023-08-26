package com.demoqa.specs;

import io.restassured.specification.RequestSpecification;

import static com.demoqa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {
    public static RequestSpecification accountRequestWithBodySpecification = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .basePath("Account/v1/");

    public static RequestSpecification accountRequestWithoutBodySpecification = with()
            .log().uri()
            .log().method()
            .filter(withCustomTemplates())
            .basePath("Account/v1/");

    public static RequestSpecification booksRequestSpecification = with()
            .log().uri()
            .log().method()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .basePath("BookStore/v1/");
}
