package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.api.AccountAPI;
import com.demoqa.api.BooksAPI;
import com.demoqa.config.WebConfig;
import com.demoqa.helpers.Attach;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginAndRegistrationRequestModel;
import com.demoqa.pages.CookiesPage;
import com.demoqa.pages.LoginPage;
import com.demoqa.pages.ProfilePage;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
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
        Configuration.holdBrowserOpen = false;
        Configuration.pollingInterval = 400;
        Configuration.baseUrl = config.baseUrl();
        RestAssured.baseURI = "https://demoqa.com/";
        Configuration.browser = config.browser();
        Configuration.browserSize = config.browserSize();
        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.timeout = 4000;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}

