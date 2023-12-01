package com.example.tinylib.model.responses;

import com.example.tinylib.repository.LibraryBook;

import java.util.List;

/**
 * LibraryViewResponse is a record representing the response
 * in /api/library/view_books endpoint.
 *
 * @param timestamp The time of the response.
 * @param path The path of the endpoint.
 * @param status The http status code.
 * @param items The total number of books in the library.
 * @param books A list with the books of the library.
 */
public record LibraryViewResponse(
        String timestamp,
        String path,
        int status,
        int items,
        List<LibraryBook> books) { }
