package com.example.tinylib.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing library books.
 */
@Repository
public interface LibraryRepository extends CrudRepository<LibraryBook,String> {

    /**
     * Returns the number of occurrences with the given title.
     * It should always be one. Used to avoid inserting duplicate
     * books in the library. In such case the number of copies should
     * be increased.
     *
     * @param title The title of the book
     * @return The number of occurrences with the given title.
     */
    @Query(value ="SELECT COUNT(*) FROM LibraryBook WHERE title=:title")
    int searchByTitle(@Param("title") String title);

    /**
     * Returns the book with the given title if it exists in the library.
     * @param title The title of the book to search for.
     * @return The book if exists otherwise null.
     */
    @Query(value = "SELECT book FROM LibraryBook book WHERE book.title=:title")
    LibraryBook retrieveBook(@Param("title") String title);
}
