package com.example.tinylib.model.responses;

/**
 * The delete response object.
 * @param id The id of the book or invalid if exceptions occur.
 * @param path The path of the endpoint.
 * @param action The outcome of the request.
 * @param status The http status code.
 * @param timestamp The time of the response.
 */
public record BookDeleteResponse(
        String id,
        String path,
        String action,
        int status,
        String timestamp) { }
