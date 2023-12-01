package com.example.tinylib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing library books.
 */
@Repository
public interface LibraryRepository extends CrudRepository<LibraryBook,String> {
}
