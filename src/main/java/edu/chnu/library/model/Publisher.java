package edu.chnu.library.model;

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
 * @date 15.08.2022 12:37
 * @class Publisher
 */
@Entity
@Table(name = "publishers")
@Document("publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Publisher(String id) {
        this.id = id;
    }

    public Publisher(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id.equals(publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created_at=" + createdAt +
                ", update_at=" + updatedAt +
                '}';
    }
}
