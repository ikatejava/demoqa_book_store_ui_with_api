package com.demoqa.models;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseModel {
    String userId, username;
    List<BookModel> books;
}
