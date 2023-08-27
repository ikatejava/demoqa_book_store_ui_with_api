package com.demoqa.pages;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookiesPage {
    String loginPagePath = "favicon.ico";

    public CookiesPage openFileToAddCookies() {
        open(loginPagePath);
        return this;
    }

    public CookiesPage addCookies(String name, String value) {
        getWebDriver().manage().addCookie(new Cookie(name, value));
        return this;
    }
}
