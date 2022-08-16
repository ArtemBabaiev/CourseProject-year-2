package edu.babaiev.libr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 12:33
 * @class Author
 */
@Entity
@Table(name = "authors")
@Document("authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime update_at;

    public Author(String id) {
        this.id = id;
    }

    public Author(String name, String description, LocalDateTime created_at, LocalDateTime update_at) {
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }
}
