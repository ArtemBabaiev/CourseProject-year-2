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
 * @class ReadingRoom
 */
@Entity
@Table(name = "reading_rooms")
@Document("reading_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingRoom {
    @Id
    private String id;
    private String number;
    @DBRef
    @ManyToOne
    private Library library;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReadingRoom(String id) {
        this.id = id;
    }

    public ReadingRoom(String number, Library library, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.number = number;
        this.library = library;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingRoom that = (ReadingRoom) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReadingRoom{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", library=" + library +
                ", created_at=" + createdAt +
                ", update_at=" + updatedAt +
                '}';
    }
}
