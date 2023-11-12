package com.demoqa.utils;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DriverCookieUtils {
    String loginPagePath = "/favicon.ico";

    public DriverCookieUtils openFileToAddCookies() {
        open(loginPagePath);
        return this;
    }

    public DriverCookieUtils addCookies(String name, String value) {
        getWebDriver().manage().addCookie(new Cookie(name, value));
        return this;
    }
}
