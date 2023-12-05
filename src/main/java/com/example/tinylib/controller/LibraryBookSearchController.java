package com.example.tinylib.controller;

import com.example.tinylib.model.responses.BookSearchResponse;
import com.example.tinylib.repository.LibraryBook;
import com.example.tinylib.repository.LibraryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The LibraryBookSearch Controller is searching the library
 * for the requested book.
 */
@RestController
@Transactional
public class LibraryBookSearchController {

    /**
     * The repository for accessing the library.
     */
    private final LibraryRepository libraryRepository;

    /**
     * Constructs a new LibrarySearchController with the specified libraryRepository.
     * @param libraryRepository The LibraryRepository instance to be injected
     */
    @Autowired
    public LibraryBookSearchController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Handles requests to the endpoint /api/library/search_book to search for
     * a book in the library.
     * @param title The title of the book to search for.
     * @return An Object with the book if it exists, otherwise an object with
     *         the field book null.
     */
    @PostMapping(value = "/api/library/search_book", produces = "application/json")
    public BookSearchResponse searchBook(@RequestParam("title") String title) {

        //TODO validate title;

        LibraryBook searchBookResults = libraryRepository.retrieveBook(title);
        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        if (searchBookResults == null) {
            return new BookSearchResponse(
                    timestampObj.format(formatTime),
                    "/api/library/search_book",
                    HttpStatus.NOT_FOUND.value(),
                    null);
        }

        return new BookSearchResponse(
                timestampObj.format(formatTime),
                "/api/library/search_book",
                HttpStatus.OK.value(),
                searchBookResults);
    }

}
