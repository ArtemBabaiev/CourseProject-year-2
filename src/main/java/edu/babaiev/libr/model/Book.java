package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 22:16
 * @class Book
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends Literature {
    @DBRef
    @ManyToOne
    private Author author;
    @DBRef
    @ManyToOne
    private Genre genre;
    @DBRef
    @ManyToOne
    private BookType bookType;
    @DBRef
    @ManyToOne
    private Writing writing;

    public Book(String id) {
        super(id);
    }

    public Book(String isn, String name, int publishingYear, Publisher publisher, Shelf shelf, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime updated_at, Author author, Genre genre, BookType bookType, Writing writing) {
        super(isn, name, publishingYear, publisher, shelf, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, updated_at);
        this.author = author;
        this.genre = genre;
        this.bookType = bookType;
        this.writing = writing;
    }

    public Book(String id, String isn, String name, int publishingYear, Publisher publisher, Shelf shelf, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime updated_at, Author author, Genre genre, BookType bookType, Writing writing) {
        super(id, isn, name, publishingYear, publisher, shelf, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, updated_at);
        this.author = author;
        this.genre = genre;
        this.bookType = bookType;
        this.writing = writing;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", genre=" + genre +
                ", bookType=" + bookType +
                ", writing=" + writing +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
