package com.demoqa.tests;

import com.demoqa.api.AccountAPI;
import com.demoqa.models.AddBookModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.demoqa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class BooksTests extends TestBase {
    @Test
    @Tag("books")
    @Tag("positive")
    @DisplayName("Add books to user profile via API")
    void addBooksToProfileSuccessfulTest() {
        step("Log in and add books to profile via API", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());
        });
        step("Check that the books are really added to user's profile on website", () -> {
            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .checkGitPocketGuidePresence()
                    .checkDesigningWebAPIsPresense();
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("negative")
    @DisplayName("Try to add book with non-existent ISBN to user profile via API (failure)")
    void addBookWithNonExistentISBNTest() {
        step("Log in and try to add book with non-existent ISBN to profile via API", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(nonExistentBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookWithNonExistentISBN(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());
        });
        step("Check user's profile on website", () -> {
            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName());
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("negative")
    @DisplayName("Try to add book to user profile via API without sending authorization token (failure)")
    void addBookToUnauthorizedProfileTest() {
        step("Log in and try to add books to profile without sending authorization token", () -> {

            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToUnauthorizedProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());
        });
        step("Check user's profile on website", () -> {
            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .verifyGitPocketGuideIsAbsent()
                    .verifyDesigningWebAPIsIsAbsent();
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("positive")
    @DisplayName("Delete all books from profile collection via API")
    void deleteAllBooksFromProfileTest() {
        step("Authorize, add some books to profile, then delete all of them via API", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);
            isbnList.add(youDontKnowJSBook);
            isbnList.add(eloquentJSBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .checkGitPocketGuidePresence()
                    .checkDesigningWebAPIsPresense()
                    .checkYouDontKnowJSPresense()
                    .checkEloquentJSPresense();

            booksAPI.deleteAllBooks(loginResponse);
        });
        step("Check that all books have been deleted", () -> {
            profilePage.verifyBooksDeletedFromProfile();
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("negative")
    @DisplayName("Try to delete books from profile collection via API without sending authorization token (failure)")
    void deleteAllBooksFromUnauthorizedProfileTest() {
        step("Log in, add books, try to delete them without sending authorization token", () -> {
            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);
            isbnList.add(youDontKnowJSBook);
            isbnList.add(eloquentJSBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .checkGitPocketGuidePresence()
                    .checkDesigningWebAPIsPresense()
                    .checkYouDontKnowJSPresense()
                    .checkEloquentJSPresense();

            booksAPI.deleteAllBooksFromUnauthorizedProfile(loginResponse);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("positive")
    @DisplayName("Delete 1 book from profile collection via API")
    void deleteBookFromProfileTest() {
        step("Authorize, add some books, delete one of them via API", () -> {

            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);
            isbnList.add(youDontKnowJSBook);
            isbnList.add(eloquentJSBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .checkGitPocketGuidePresence()
                    .checkDesigningWebAPIsPresense()
                    .checkYouDontKnowJSPresense()
                    .checkEloquentJSPresense();

            DeleteBookModel deleteEloquentJS = new DeleteBookModel(eloquentJSISBN, loginResponse.getUserId());
            booksAPI.deleteBook(deleteEloquentJS, loginResponse);
        });
        step("Check that the book has been deleted from profile", () -> {
            profilePage.verifyEloquentJSIsAbsent();
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("negative")
    @DisplayName("Try to delete book with non-existent ISBN from profile collection via API (failure)")
    void deleteBookWithNonExistentISBNTest() {
        step("Authorize, add some books, try to delete one of them via API", () -> {

            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);
            isbnList.add(youDontKnowJSBook);
            isbnList.add(eloquentJSBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .checkGitPocketGuidePresence()
                    .checkDesigningWebAPIsPresense()
                    .checkYouDontKnowJSPresense()
                    .checkEloquentJSPresense();

            DeleteBookModel deleteNonExistentBook = new DeleteBookModel(nonExistentISBN, loginResponse.getUserId());
            booksAPI.deleteBookWithNonExistentISBN(deleteNonExistentBook, loginResponse);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }

    @Test
    @Tag("books")
    @Tag("negative")
    @DisplayName("Try to delete 1 book from unathorized user profile collection (failure)")
    void deleteBookFromUnauthorizedProfileTest() {
        step("Try to delete book without sending authorization token (failure)", () -> {

            LoginResponseModel loginResponse = AccountAPI.login(validAuthData);
            booksAPI.deleteAllBooks(loginResponse);

            isbnList.add(gitPocketGuideBook);
            isbnList.add(designingWebAPIsBook);
            isbnList.add(youDontKnowJSBook);
            isbnList.add(eloquentJSBook);

            AddBookModel booksList = new AddBookModel(loginResponse.getUserId(), isbnList);

            booksAPI.addBookToProfile(loginResponse, booksList);

            cookiesPage.openFileToAddCookies()
                    .addCookies(userIdCookie, loginResponse.getUserId())
                    .addCookies(tokenCookie, loginResponse.getToken())
                    .addCookies(expiresCookie, loginResponse.getExpires());

            profilePage.openProfilePage()
                    .checkMainHeader()
                    .checkAuthorizedUserName(validAuthData.getUserName())
                    .checkGitPocketGuidePresence()
                    .checkDesigningWebAPIsPresense()
                    .checkYouDontKnowJSPresense()
                    .checkEloquentJSPresense();

            DeleteBookModel deleteEloquentJS = new DeleteBookModel(eloquentJSISBN, loginResponse.getUserId());
            booksAPI.deleteBookFromUnauthorizedProfile(deleteEloquentJS, loginResponse);
        });
        step("Log out", () -> {
            profilePage.clickLogoutButton();
        });
    }
}
