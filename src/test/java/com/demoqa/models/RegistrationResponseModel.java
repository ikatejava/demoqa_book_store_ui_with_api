package com.demoqa.models;

import lombok.Data;

@Data
public class RegistrationResponseModel {
    String userID, username;
    String[] books;
}
