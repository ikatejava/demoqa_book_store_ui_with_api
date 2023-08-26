package com.demoqa.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginAndRegistrationRequestModel {
    String userName, password;
}
