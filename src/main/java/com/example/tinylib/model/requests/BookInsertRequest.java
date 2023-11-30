package com.example.tinylib.model.requests;

public record BookInsertRequest(
        String title,
        String category,
        String author,
        int copies) { }
