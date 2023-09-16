package com.demoqa.api;

import com.demoqa.models.LoginAndRegistrationRequestModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.models.RegistrationResponseModel;
import com.demoqa.models.UserInfoResponseModel;

import static com.demoqa.specs.RequestSpecs.accountRequestWithBodySpecification;
import static com.demoqa.specs.RequestSpecs.accountRequestWithoutBodySpecification;
import static com.demoqa.specs.ResponseSpecs.*;
import static com.demoqa.tests.TestData.accountUsername;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountAPI {

    public static LoginResponseModel login(LoginAndRegistrationRequestModel authData) {
        return given(accountRequestWithBodySpecification)
                .body(authData)
                .when()
                .post("Login")
                .then()
                .spec(successfulAuthorizationResponseSpec200)
                .extract().as(LoginResponseModel.class);
    }

    public static void newUser(LoginAndRegistrationRequestModel regData) {
        given(accountRequestWithBodySpecification)
                .body(regData)
                .when()
                .post("User")
                .then()
                .spec(successfulRegistrationResponseSpec201)
                .extract().as(RegistrationResponseModel.class);
    }

    public void successfulAccountDeletion(LoginResponseModel loginResponse) {
        given(accountRequestWithBodySpecification)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .delete("User/" + loginResponse.getUserId())
                .then()
                .spec(successfulDeletionResponseSpec204);
    }

    public static void getUserInfo(LoginResponseModel loginResponse) {
        UserInfoResponseModel userInfoResponseModel = step("Make request", () ->
                given(accountRequestWithoutBodySpecification)
                        .header("Authorization", "Bearer " + loginResponse.getToken())
                        .when()
                        .get("User/" + loginResponse.getUserId())
                        .then()
                        .spec(getUserInfoSuccessfulResponseSpec200)
                        .extract().as(UserInfoResponseModel.class));
        step("Check response 200", () ->
                assertEquals(accountUsername, userInfoResponseModel.getUsername()));
    }
}

