package com.example.tinylib.model;

import java.util.List;

public record LibraryViewResponse(List<Book> books, String timestamp) { }
