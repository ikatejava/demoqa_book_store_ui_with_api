package com.demoqa.tests;

import com.demoqa.api.AccountAPI;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.models.RegistrationResponseModel;
import com.demoqa.models.UserInfoResponseModel;
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
        step("Authorize via API, get response body cookies and add them to small file", () -> {
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
        step("Create new user, get response body cookies and add them to small file", () -> {
            RegistrationResponseModel registrationResponse = AccountAPI.newUser(validRegData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, registrationResponse.getUserID())
                    .addCookies(usernameCookie, registrationResponse.getUsername());
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
    @DisplayName("Successful account deletion after sending authorization token")
    void successfulAccountDeletionTest() {
        step("Create new user, get response body cookies and add them to small file", () -> {
            RegistrationResponseModel registrationResponse = AccountAPI.newUser(validRegData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, registrationResponse.getUserID())
                    .addCookies(usernameCookie, registrationResponse.getUsername());
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
        step("Check that account is deleted", () -> {
            profilePage.verifyAccountDoesntExistAnymore();
        });
        step("Logout from non-existent account", () -> {
            loginPage.openLoginPage()
                    .clickLogoutButton();
        });
    }

    @Test
    @Tag("user")
    @Tag("negative")
    @DisplayName("Try to delete account without sending authorization token (failure)")
    void unauthorizedAccountDeletionTest() {
        step("Create new user, get response body cookies and add them to small file", () -> {
            RegistrationResponseModel registrationResponse = AccountAPI.newUser(validRegData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, registrationResponse.getUserID())
                    .addCookies(usernameCookie, registrationResponse.getUsername());
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
        step("Log in via API, try to delete account without sending authorization token", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validRegData);

            accountAPI.unauthorizedAccountDeletion(loginResponse);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("user")
    @Tag("positive")
    @DisplayName("Check user information on Profile page that is: 'userId', 'username' and his/her/its book collection")
    void getUserInfoSuccessfulTest() {
        step("Authorize via API, then on website and check user's profile", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName());

            UserInfoResponseModel userInfoResponseModel = AccountAPI.getUserInfo(loginResponse);
            System.out.println(userInfoResponseModel);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("user")
    @Tag("negative")
    @DisplayName("Try to check user information on Profile page without sending authorization token (failure)")
    void getUnauthorizedUserInfoTest() {
        step("Try to check user's profile without sending authorization token", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName());

            accountAPI.getUnauthorizedUserInfo(loginResponse);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }
}




