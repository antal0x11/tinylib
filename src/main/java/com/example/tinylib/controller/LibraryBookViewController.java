package com.example.tinylib.controller;

import com.example.tinylib.model.responses.LibraryViewResponse;
import com.example.tinylib.repository.LibraryBook;
import com.example.tinylib.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller that handles requests in the /api/library/view_books
 * endpoint.
 */
@RestController
public class LibraryBookViewController {

    /**
     * The repository for accessing the library.
     */
    private final LibraryRepository libraryRepository;

    /**
     * Constructs a new LibraryBookViewController with the specified libraryRepository.
     * @param libraryRepository The LibraryRepository instance to be injected.
     */
    @Autowired
    public LibraryBookViewController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Handles requests in the /api/library/view_books endpoint.
     * @return LibraryViewResponse An object with the available books in the library.
     */
    @GetMapping(path="/api/library/view_books", produces = "application/json")
    public LibraryViewResponse ViewLibraryBooks() {

        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<LibraryBook> bookList = new ArrayList<>();

        libraryRepository.findAll().forEach(bookList::add);

        return new LibraryViewResponse(
                timestampObj.format(formatTime),
                "/api/library/view_books",
                HttpStatus.OK.value(),
                bookList.size(),
                bookList);
    }
}
