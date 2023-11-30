package com.example.tinylib.model;

public record Book(
        String id,
        String title,
        String author,
        String category,
        int copies) {}
