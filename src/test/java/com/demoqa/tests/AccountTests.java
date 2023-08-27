package com.demoqa.tests;

import com.demoqa.api.AccountAPI;
import com.demoqa.models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.demoqa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class AccountTests extends TestBase {
    @Test
    @Tag("user")
    @Tag("positive")
    @DisplayName("Successful authorization via API")
    void successfulLoginTest() {
        step("Log in via API, get response body cookies and add them to small file", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());
        });
        step("Check that the user is authorized", () -> {
            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName());
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("user")
    @Tag("positive")
    @DisplayName("Successful registration via API")
    void successfulRegistrationTest() {
        step("Create new user via API", () -> {
            AccountAPI.newUser(validRegData);
        });
        step("Fill out authorization form on website", () -> {
            loginPage.openLoginPage()
                    .inputUserName(validRegData.getUserName())
                    .inputUserPassword(validRegData.getPassword())
                    .clickLoginButton();
        });
        step("Check that the user is authorized", () -> {
            profilePage.checkMainHeader()
                    .checkAuthorizedUserName(validRegData.getUserName());
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("user")
    @Tag("positive")
    @DisplayName("Successful account deletion")
    void successfulAccountDeletionTest() {
        step("Create new user", () -> {
            AccountAPI.newUser(validRegData);
        });
        step("Fill out authorization form on website", () -> {
            loginPage.openLoginPage()
                    .inputUserName(validRegData.getUserName())
                    .inputUserPassword(validRegData.getPassword())
                    .clickLoginButton();
        });
        step("Check that the user is authorized", () -> {
            profilePage.checkMainHeader()
                    .checkAuthorizedUserName(validRegData.getUserName());
        });
        step("Log in via API so to get token, delete account", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validRegData);

            accountAPI.successfulAccountDeletion(loginResponse);
        });
        step("Check that account has been deleted", () -> {
            profilePage.verifyAccountDoesntExistAnymore();
        });
    }

    @Test
    @Tag("user")
    @Tag("positive")
    @DisplayName("Check user information on Profile page that is: 'userId', 'username' and his/her/its book collection")
    void getUserInfoSuccessfulTest() {
        step("Authorize via API then on website, and check user's profile", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName());

            AccountAPI.getUserInfo(loginResponse);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }
}




