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
     * @return int The number of occurrences with the given title.
     */
    @Query(value ="SELECT COUNT(*) FROM LibraryBook WHERE title=:title")
    int searchByTitle(@Param("title") String title);
}
