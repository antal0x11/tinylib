package com.example.tinylib.model.requests;

/**
 * The book that is requested to be deleted.
 * @param id The id of the book.
 */
public record BookDeleteRequest(String id) { }
