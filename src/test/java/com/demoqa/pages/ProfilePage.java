package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    String profilePagePath = "profile";

    SelenideElement
            mainHeader = $(".main-header"),
            userNameValue = $("#userName-value"),
            logoutButton = $("#submit"),
            userNotFoundText = $(".profile-wrapper").$("#name"),
            gitPocketGuideBook = $("a[href='/profile?book=9781449325862']"),
            designingWebAPIsBook = $("a[href='/profile?book=9781449337711']"),
            youDontKnowJSBook = $("a[href='/profile?book=9781491904244']"),
            eloquentJSBook = $("a[href='/profile?book=9781593275846']"),
            booksFieldOnProfile = $(".rt-tbody");

    public ProfilePage openProfilePage() {
        open(profilePagePath);
        return this;
    }

    public ProfilePage checkMainHeader() {
        mainHeader.shouldHave(text("Profile"));
        return this;
    }

    public ProfilePage checkAuthorizedUserName(String login) {
        userNameValue.shouldHave(text(login));
        String authorizedUserName = userNameValue.getText();
        System.out.println(authorizedUserName);
        return this;
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void verifyAccountDoesntExistAnymore() {
        refresh();
        userNotFoundText.shouldHave(text("User not found!"));
    }

    public ProfilePage checkGitPocketGuidePresence() {
        gitPocketGuideBook.shouldBe(visible);
        return this;
    }

    public ProfilePage checkDesigningWebAPIsPresense() {
        designingWebAPIsBook.shouldBe(visible);
        return this;
    }

    public ProfilePage checkYouDontKnowJSPresense() {
        youDontKnowJSBook.shouldBe(visible);
        return this;
    }

    public void checkEloquentJSPresense() {
        eloquentJSBook.shouldBe(visible);
    }

    public void verifyProfileHasNoBooks() {
        refresh();
        booksFieldOnProfile.shouldBe(empty);
    }

    public void verifyEloquentJSIsAbsent() {
        refresh();
        eloquentJSBook.shouldNotBe(visible);
    }
}
