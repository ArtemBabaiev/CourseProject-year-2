package edu.babaiev.libr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 12:34
 * @class Writing
 */
@Entity
@Table(name = "writings")
@Document("writings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Writing {
    @Id
    private String id;
    private String name;
    @DBRef
    @ManyToMany
    @JoinTable(name = "writing_has_authors")
    private Set<Author> authors;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime update_at;

    public Writing(String id) {
        this.id = id;
    }

    public Writing(String name, Set<Author> authors, String description, LocalDateTime created_at, LocalDateTime update_at) {
        this.name = name;
        this.authors = authors;
        this.description = description;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writing writing = (Writing) o;
        return id.equals(writing.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Writing{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }
}
