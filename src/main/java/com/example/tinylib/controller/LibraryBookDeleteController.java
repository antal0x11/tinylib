package com.example.tinylib.controller;

import com.example.tinylib.model.requests.BookDeleteRequest;
import com.example.tinylib.model.responses.BookDeleteResponse;
import com.example.tinylib.repository.LibraryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The LibraryBookDeleteController deletes a book from the library.
 */
@RestController
public class LibraryBookDeleteController {

    /**
     * The repository for accessing the library.
     */
    private final LibraryRepository libraryRepository;

    /**
     * The logger instance that keeps track of errors.
     */
    private final Logger logger = LoggerFactory.getLogger(LibraryBookDeleteController.class);

    @Autowired
    public LibraryBookDeleteController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @DeleteMapping(path="/api/library/delete_book")
    @Transactional
    public BookDeleteResponse deleteLibraryBook(@RequestBody String request) {

        //TODO add validation

        ObjectMapper objectMapper = new ObjectMapper();

        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String tm = timestampObj.format(formatTime);

        try {

            BookDeleteRequest req = objectMapper.readValue(request, BookDeleteRequest.class);
            libraryRepository.deleteById(req.id());

            return new BookDeleteResponse(
                    req.id(),
                    "/api/library/delete_book",
                    "complete",
                    HttpStatus.OK.value(),
                    tm
            );
        } catch (JsonProcessingException e) {
            logger.error(e.toString());
            return new BookDeleteResponse(
                    "invalid",
                    "/api/library/delete_book",
                    "fail",
                    HttpStatus.BAD_REQUEST.value(),
                    tm
            );
        }
    }
}
