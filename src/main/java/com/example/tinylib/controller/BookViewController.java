package com.example.tinylib.controller;

import com.example.tinylib.model.Book;
import com.example.tinylib.model.LibraryViewResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookViewController {

    @GetMapping(path="/api/library/view_books", produces = "application/json")
    public LibraryViewResponse ViewLibraryBooks() {
        Book b1 = new Book("qwer-123-asdf", "The gray wolf");
        Book b2 = new Book("sadf-532-sfds", "The Big Blue Eyes");

        List<Book> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);

        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return new LibraryViewResponse(bookList, timestampObj.format(formatTime));
    }
}
