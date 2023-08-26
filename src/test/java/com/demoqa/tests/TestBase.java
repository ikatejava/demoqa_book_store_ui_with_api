package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.api.AccountAPI;
import com.demoqa.api.BooksAPI;
import com.demoqa.config.WebConfig;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginAndRegistrationRequestModel;
import com.demoqa.pages.CookiesPage;
import com.demoqa.pages.LoginPage;
import com.demoqa.pages.ProfilePage;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static com.demoqa.tests.TestData.*;


public class TestBase {
    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    CookiesPage cookiesPage = new CookiesPage();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();

    AccountAPI accountAPI = new AccountAPI();
    BooksAPI booksAPI = new BooksAPI();

    LoginAndRegistrationRequestModel validAuthData = new LoginAndRegistrationRequestModel(accountUsername,
            accountValidPassword);
    Faker faker = new Faker();
    String fakerUsername = faker.name().username();
    LoginAndRegistrationRequestModel validRegData = new LoginAndRegistrationRequestModel(fakerUsername,
            passwordForRegistration);

    IsbnModel gitPocketGuideBook = new IsbnModel(gitPocketGuideISBN);
    IsbnModel designingWebAPIsBook = new IsbnModel(designingWebAPIsISBN);
    IsbnModel youDontKnowJSBook = new IsbnModel(youDontKnowJSISBN);
    IsbnModel eloquentJSBook = new IsbnModel(eloquentJSISBN);
    IsbnModel nonExistentBook = new IsbnModel(nonExistentISBN);

    List<IsbnModel> isbnList = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.pollingInterval = 400;
        Configuration.baseUrl = config.baseUrl();
        RestAssured.baseURI = "https://demoqa.com/";
        Configuration.browser = config.browser();
        Configuration.browserSize = config.browserSize();
        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();
        }
    }
}

