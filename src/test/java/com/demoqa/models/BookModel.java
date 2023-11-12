package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookModel {
    String isbn, title, subTitle, author, publisher, description, website;
    @JsonProperty("publish_date")
    String publishDate;
    int pages;
}
