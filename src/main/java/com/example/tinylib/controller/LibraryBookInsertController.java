package com.example.tinylib.controller;

import com.example.tinylib.model.requests.BookInsertRequest;
import com.example.tinylib.model.responses.BookInsertResponse;
import com.example.tinylib.repository.LibraryBook;
import com.example.tinylib.repository.LibraryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The LibraryBookInsertController inserts a book in
 * the library.
 */
@RestController
public class LibraryBookInsertController {

    /**
     * The repository for accessing the library.
     */
    private final LibraryRepository libraryRepository;

    /**
     * The logger instance that keeps track of errors.
     */
    private final Logger logger = LoggerFactory.getLogger(LibraryBookInsertController.class);

    /**
     * Constructs a new LibraryBookInsertController with the specified libraryRepository.
     * @param libraryRepository The LibraryRepository instance to be injected.
     */
    @Autowired
    public LibraryBookInsertController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Handles requests on the /api/library/insert_book endpoint to insert a
     * book.
     *
     * @param request A JSON object with the required fields of the book.
     * @return BookInsertResponse An object that contains the response either success of fail.
     */
    @PostMapping(path="/api/library/insert_book", consumes = "application/json",produces = "application/json")
    @Transactional
    public BookInsertResponse insertLibraryBook(@RequestBody String request) {

        //TODO add validation of the request and response for errors

        ObjectMapper objectMapper = new ObjectMapper();

        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String tm = timestampObj.format(formatTime);

        try {

            BookInsertRequest req = objectMapper.readValue(request, BookInsertRequest.class);
            LibraryBook libraryBook = new LibraryBook(req);

            String searchDuplicateTitle = libraryBook.getTitle();
            int duplicate = libraryRepository.searchByTitle(searchDuplicateTitle);

            if (duplicate != 0) return new BookInsertResponse("" +
                    "Duplicate Book",
                    "/api/library/insert_book",
                    "aborted",
                    HttpStatus.CONFLICT.value(),
                    tm);
            libraryRepository.save(libraryBook);

            return new BookInsertResponse(
                    req.title(),
                    "/api/library/insert_book",
                    "success",
                    HttpStatus.OK.value(),
                    tm);
        } catch(JsonProcessingException e) {
            logger.error(e.toString());
            return new BookInsertResponse(
                    "invalid",
                    "/api/library/insert_book",
                    "fail",
                    HttpStatus.BAD_REQUEST.value(),
                    tm);
        }
    }
}
