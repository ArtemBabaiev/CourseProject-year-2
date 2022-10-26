package edu.chnu.library.model;

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
 * @class BookCase
 */
@Entity
@Table(name = "book_cases")
@Document("book_cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCase {
    @Id
    private String id;
    private String number;
    @DBRef
    @ManyToOne
    private ReadingRoom readingRoom;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookCase(String id) {
        this.id = id;
    }

    public BookCase(String number, ReadingRoom readingRoom, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.number = number;
        this.readingRoom = readingRoom;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCase bookCase = (BookCase) o;
        return id.equals(bookCase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookCase{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", readingRoom=" + readingRoom +
                ", created_at=" + createdAt +
                ", update_at=" + updatedAt +
                '}';
    }
}
