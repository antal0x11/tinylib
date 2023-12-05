package com.example.tinylib.model.responses;

import com.example.tinylib.repository.LibraryBook;

/**
 * The response object when a request to search for
 * a book was made.
 *
 * @param timestamp The time of the response.
 * @param path The path of the endpoint
 * @param status The http status code.
 * @param book The book that exists in the library or null if it
 *             doesn't exist.
 */
public record BookSearchResponse(
        String timestamp,
        String path,
        int status,
        LibraryBook book) { }
