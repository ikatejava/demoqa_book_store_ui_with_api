package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    String loginPagePath = "/login";
    SelenideElement
            userName = $("#userName"),
            userPassword = $("#password"),
            loginButton = $("#login");

    public LoginPage openLoginPage() {
        open(loginPagePath);
        return this;
    }

    public LoginPage inputUserName(String login) {
        userName.setValue(login);
        return this;
    }

    public LoginPage inputUserPassword(String password) {
        userPassword.setValue(password);
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
