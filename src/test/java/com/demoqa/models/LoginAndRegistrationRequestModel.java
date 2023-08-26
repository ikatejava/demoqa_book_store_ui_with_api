package com.demoqa.models;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginAndRegistrationRequestModel {
    String userName, password;
}
