package com.demoqa.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class AddBookModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}
