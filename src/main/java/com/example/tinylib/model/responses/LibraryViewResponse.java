package com.example.tinylib.model.responses;

import com.example.tinylib.model.Book;

import java.util.List;

public record LibraryViewResponse(
        String timestamp,
        int items,
        List<Book> books) { }
