package com.demoqa.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBookModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}
