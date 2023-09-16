package com.demoqa.models;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationResponseModel {
    String userID, username;
    List<BookModel> books;
}
