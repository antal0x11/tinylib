package com.example.tinylib.controller;

import com.example.tinylib.model.Book;
import com.example.tinylib.model.responses.LibraryViewResponse;
import com.example.tinylib.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryBookViewController {

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping(path="/api/library/view_books", produces = "application/json")
    public LibraryViewResponse ViewLibraryBooks() {

        List<Book> bookList = new ArrayList<>();

        libraryRepository.findAll().forEach( book -> {
            Book tmpBook = new Book(book.getId(),book.getTitle(),book.getAuthor(), book.getCategory(), book.getCopies());
            bookList.add(tmpBook);
        });

        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return new LibraryViewResponse(
                timestampObj.format(formatTime),
                bookList.size(),
                bookList);
    }
}
