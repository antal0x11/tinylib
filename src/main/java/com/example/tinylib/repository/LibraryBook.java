package com.example.tinylib.repository;

import com.example.tinylib.model.requests.BookInsertRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

/**
 * LibaryBook represents a book in the library.
 */
@Entity
@Table(name = "books")
public class LibraryBook {

    /**
     * The id of the book.
     */
    @Id
    private String id;

    /**
     * The title of the book.
     */
    private String title;

    /**
     * The category of the book.
     */
    private String category;

    /**
     * The author of the book.
     */
    private String author;

    /**
     * The number of copies of the book.
     */
    private int copies;

    /**
     * Default LibraryBook constructor.
     */
    public LibraryBook() {
        this.id = null;
        this.title = null;
        this.category = null;
        this.author = null;
        this.copies = 0;
    }

    /**
     * Creates a LibraryBook object from the book request
     * that we need to add in the library.
     * @param req The book request object.
     */
    public LibraryBook(BookInsertRequest req) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.title = req.title();
        this.category = req.category();
        this.author = req.author();
        this.copies = req.copies();
    }

    /**
     * A copy constructor of the LibraryBook class.
     * @param book The LibraryBook object.
     */
    public LibraryBook(LibraryBook book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.category = book.getCategory();
        this.copies = book.getCopies();
    }

    /**
     * Returns the book id.
     * @return String The id of the book.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the book title.
     * @return String The book title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the book category.
     * @return String The book category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the author of the book.
     * @return String The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Reuturns the number of the copies of the book.
     * @return int The number of the copies of the book.
     */
    public int getCopies() {
        return copies;
    }

    /**
     * Updates the id of a book.
     * @param id The id to set for the book.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Updates the title of the book.
     * @param title The title to set for the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Updates the author of the book.
     * @param author The author name to set for the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Updates the category of the book.
     * @param category The category to set for the book.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Updates the number of copies of the book.
     * @param copies The number of copies to set for the book.
     */
    public void setCopies(int copies) {
        this.copies = copies;
    }
}
