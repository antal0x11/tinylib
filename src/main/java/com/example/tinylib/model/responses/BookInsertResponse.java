package com.example.tinylib.model.responses;

/**
 * The response object when a book is inserted in the library
 * or when a book fails to be inserted.
 *
 * @param title The title of the book or invalid if it fails.
 * @param path The path of the endpoint.
 * @param action The outcome of the request
 * @param status The http status code.
 * @param timestamp The time of the response.
 */
public record BookInsertResponse(
        String title,
        String path,
        String action,
        int status,
        String timestamp) { }
