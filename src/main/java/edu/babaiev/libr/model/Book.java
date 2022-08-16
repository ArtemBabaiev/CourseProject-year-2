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
    private Genre genre;
    @DBRef
    @ManyToOne
    private BookType bookType;
    @DBRef
    @ManyToOne
    private Writing writing;

    public Book(String isn, String name, int publishingYear, Publisher publisher, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime update_at, Genre genre, BookType bookType, Writing writing) {
        super(isn, name, publishingYear, publisher, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, update_at);
        this.genre = genre;
        this.bookType = bookType;
        this.writing = writing;
    }

    public Book(String id, String isn, String name, int publishingYear, Publisher publisher, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime update_at, Genre genre, BookType bookType, Writing writing) {
        super(id, isn, name, publishingYear, publisher, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, update_at);
        this.genre = genre;
        this.bookType = bookType;
        this.writing = writing;
    }

    @Override
    public String toString() {
        return "Book{" +
                "genre=" + genre +
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