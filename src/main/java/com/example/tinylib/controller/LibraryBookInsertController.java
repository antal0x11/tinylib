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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class LibraryBookInsertController {

    @Autowired
    private LibraryRepository libraryRepository;

    private final Logger logger = LoggerFactory.getLogger(LibraryBookInsertController.class);

    @PostMapping(path="/api/library/insert_book", consumes = "application/json",produces = "application/json")
    @Transactional
    public BookInsertResponse insertLibraryBook(@RequestBody String request) {

        ObjectMapper objectMapper = new ObjectMapper();

        LocalDateTime timestampObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String tm = timestampObj.format(formatTime);

        UUID uuid = UUID.randomUUID();

        try {
            BookInsertRequest req = objectMapper.readValue(request, BookInsertRequest.class);

            LibraryBook libraryBook = new LibraryBook();

            libraryBook.setId(uuid.toString());
            libraryBook.setTitle(req.title());
            libraryBook.setCategory(req.category());
            libraryBook.setAuthor(req.author());
            libraryBook.setCopies(req.copies());

            libraryRepository.save(libraryBook);

            return new BookInsertResponse("success",tm);
        } catch(JsonProcessingException e) {
            logger.error(e.toString());
            return new BookInsertResponse("fail",tm);
        }
    }
}
