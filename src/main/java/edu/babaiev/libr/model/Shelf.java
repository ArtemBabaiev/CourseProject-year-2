package edu.babaiev.libr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 12:37
 * @class Shelf
 */
@Entity
@Table(name = "shelves")
@Document("shelves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shelf {
    @Id
    private String id;
    private String number;
    @DBRef
    @ManyToOne
    private BookCase bookCase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Shelf(String id) {
        this.id = id;
    }

    public Shelf(String number, BookCase bookCase, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.number = number;
        this.bookCase = bookCase;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", bookCase=" + bookCase +
                ", created_at=" + createdAt +
                ", update_at=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return id.equals(shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
