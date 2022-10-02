package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 18:04
 * @class WrittenOff
 */
@Entity
@Table(name = "written_off")
@Document("written_off")
@Getter
@Setter
@NoArgsConstructor
public class WrittenOff {
    @Id
    private String id;
    @Column(unique = true)
    private String isn;
    private String name;
    private int publishingYear;
    @DBRef
    @ManyToOne
    private Publisher publisher;
    private int quantity;
    private int numberOfPages;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WrittenOff(String id) {
        this.id = id;
    }

    public WrittenOff(String isn, String name, int publishingYear, Publisher publisher, int quantity, int numberOfPages, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.isn = isn;
        this.name = name;
        this.publishingYear = publishingYear;
        this.publisher = publisher;
        this.quantity = quantity;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public WrittenOff(String id, String isn, String name, int publishingYear, Publisher publisher, int quantity, int numberOfPages, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.isn = isn;
        this.name = name;
        this.publishingYear = publishingYear;
        this.publisher = publisher;
        this.quantity = quantity;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "WrittenOff{" +
                "id='" + id + '\'' +
                ", isn='" + isn + '\'' +
                ", name='" + name + '\'' +
                ", publishingYear=" + publishingYear +
                ", publisher=" + publisher +
                ", quantity=" + quantity +
                ", numberOfPages=" + numberOfPages +
                ", description='" + description + '\'' +
                ", created_at=" + createdAt +
                ", update_at=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrittenOff that = (WrittenOff) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
