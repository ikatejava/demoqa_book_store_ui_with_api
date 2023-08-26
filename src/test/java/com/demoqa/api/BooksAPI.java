package com.demoqa.api;

import com.demoqa.models.*;
import static com.demoqa.specs.RequestSpecs.*;
import static com.demoqa.specs.ResponseSpecs.*;
import static com.demoqa.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooksAPI {

    public void addBookToProfile(LoginResponseModel loginResponse, AddBookModel booksList) {
        given(booksRequestSpecification)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("Books")
                .then()
                .statusCode(201);
    }

    public void addBookWithNonExistentISBN(LoginResponseModel loginResponse, AddBookModel booksList) {
        MistakesResponseModel mistakesResponse = step("Make request", () -> {
            return given(booksRequestSpecification)
                    .header("Authorization", "Bearer " + loginResponse.getToken())
                    .body(booksList)
                    .when()
                    .post("Books")
                    .then()
                    .spec(NonExistentISBNResponse400)
                    .extract().as(MistakesResponseModel.class);
        });
        step("Check response 400", () -> {
            assertEquals(responseCode1205, mistakesResponse.getCode());
            assertEquals(responseCode1205Message, mistakesResponse.getMessage());
            System.out.println("\"code\": " + mistakesResponse.getCode() + ",\n\"message\": "
                    + mistakesResponse.getMessage());
        });
    }

    public void addBookToUnauthorizedProfile(LoginResponseModel loginResponse, AddBookModel booksList) {
        MistakesResponseModel mistakesResponse = step("Make request", () -> {
            return given(booksRequestSpecification)
                    .body(booksList)
                    .when()
                    .post("Books")
                    .then()
                    .spec(unauthorizedAccountFailedTestsResponseSpec401)
                    .extract().as(MistakesResponseModel.class);
        });
        step("Check response 401", () -> {
            assertEquals(responseCode1200, mistakesResponse.getCode());
            assertEquals(responseCode1200Message, mistakesResponse.getMessage());
            System.out.println("\"code\": " + mistakesResponse.getCode() + ",\n\"message\": "
                    + mistakesResponse.getMessage());
        });
    }

    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(booksRequestSpecification)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("Books")
                .then()
                .spec(successfulDeletionResponseSpec204);
    }

    public void deleteAllBooksFromUnauthorizedProfile(LoginResponseModel loginResponse) {
        MistakesResponseModel mistakesResponse = step("Make request", () -> {
            return given(booksRequestSpecification)
                    .queryParam("UserId", loginResponse.getUserId())
                    .when()
                    .delete("Books")
                    .then()
                    .spec(unauthorizedAccountFailedTestsResponseSpec401)
                    .extract().as(MistakesResponseModel.class);
        });
        step("Check response 401", () -> {
            assertEquals(responseCode1200, mistakesResponse.getCode());
            assertEquals(responseCode1200Message, mistakesResponse.getMessage());
            System.out.println("\"code\": " + mistakesResponse.getCode() + ",\n\"message\": "
                    + mistakesResponse.getMessage());
        });
    }

    public void deleteBook(DeleteBookModel deleteBookData, LoginResponseModel loginResponse) {
        given(booksRequestSpecification)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBookData)
                .when()
                .delete("Book")
                .then()
                .spec(successfulDeletionResponseSpec204);
    }

    public void deleteBookWithNonExistentISBN(DeleteBookModel deleteBookData, LoginResponseModel loginResponse) {
        MistakesResponseModel mistakesResponse = step("Make request", () -> {
            return given(booksRequestSpecification)
                    .header("Authorization", "Bearer " + loginResponse.getToken())
                    .body(deleteBookData)
                    .when()
                    .delete("Book")
                    .then()
                    .spec(NonExistentISBNResponse400)
                    .extract().as(MistakesResponseModel.class);
        });
        step("Check response 400", () -> {
            assertEquals(responseCode1206, mistakesResponse.getCode());
            assertEquals(responseCode1206Message, mistakesResponse.getMessage());
            System.out.println("\"code\": " + mistakesResponse.getCode() + ",\n\"message\": "
                    + mistakesResponse.getMessage());
        });
    }

    public void deleteBookFromUnauthorizedProfile(DeleteBookModel deleteBookData, LoginResponseModel loginResponse) {
        MistakesResponseModel mistakesResponse = step("Make request", () -> {
            return given(booksRequestSpecification)
                    .when()
                    .delete("Book")
                    .then()
                    .spec(unauthorizedAccountFailedTestsResponseSpec401)
                    .extract().as(MistakesResponseModel.class);
        });
        step("Check response 401", () -> {
            assertEquals(responseCode1200, mistakesResponse.getCode());
            assertEquals(responseCode1200Message, mistakesResponse.getMessage());
            System.out.println("\"code\": " + mistakesResponse.getCode() + ",\n\"message\": "
                    + mistakesResponse.getMessage());
        });
    }
}
