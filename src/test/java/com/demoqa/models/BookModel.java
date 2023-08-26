package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class BookModel {
    String isbn, title, subTitle, author, publish_date, publisher, description, website;
    int pages;
}
