package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 22:23
 * @class Collection
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Collection extends Literature {
    @DBRef
    @ManyToOne
    private Author author;
    @DBRef
    @ManyToOne
    private Genre genre;
    @DBRef
    @ManyToOne
    private CollectionType collectionType;
    @DBRef
    @ManyToMany
    @JoinTable(name = "collection_has_writings")
    private Set<Writing> writings;

    public Collection(String id) {
        super(id);
    }

    public Collection(String isn, String name, int publishingYear, Publisher publisher, Shelf shelf, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime updated_at, Author author, Genre genre, CollectionType collectionType, Set<Writing> writings) {
        super(isn, name, publishingYear, publisher, shelf, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, updated_at);
        this.author = author;
        this.genre = genre;
        this.collectionType = collectionType;
        this.writings = writings;
    }

    public Collection(String id, String isn, String name, int publishingYear, Publisher publisher, Shelf shelf, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime updated_at, Author author, Genre genre, CollectionType collectionType, Set<Writing> writings) {
        super(id, isn, name, publishingYear, publisher, shelf, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, updated_at);
        this.author = author;
        this.genre = genre;
        this.collectionType = collectionType;
        this.writings = writings;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "genre=" + genre +
                ", collectionType=" + collectionType +
                ", writings=" + writings +
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
