package com.example.tinylib.model.requests;

/**
 * The Book object request to be inserted in the library.
 * @param title The title of the book.
 * @param category The category of the book.
 * @param author The author of the book.
 * @param publisher The publisher of the book.
 * @param location The location of the library where the book is.
 * @param copies The number of copies of the book.
 */
public record BookInsertRequest(
        String title,
        String category,
        String author,
        String publisher,
        String location,
        int copies) { }
