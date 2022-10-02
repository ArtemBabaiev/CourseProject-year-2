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
 * @date 15.08.2022 12:36
 * @class Literature
 */
@Entity
@Table(name = "literature")
@Document("literature")
@Getter
@Setter
@NoArgsConstructor
public abstract class Literature {
    @Id
    private String id;
    @Column(unique = true)
    private String isn;
    private String name;
    private int publishingYear;
    @DBRef
    @ManyToOne
    private Publisher publisher;
    private boolean isLendable;
    private int numberOfPages;
    private int lendPeriodInDays;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Literature(String id) {
        this.id = id;
    }

    public Literature(String isn, String name, int publishingYear, Publisher publisher, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.isn = isn;
        this.name = name;
        this.publishingYear = publishingYear;
        this.publisher = publisher;
        this.isLendable = isLendable;
        this.numberOfPages = numberOfPages;
        this.lendPeriodInDays = lendPeriodInDays;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Literature(String id, String isn, String name, int publishingYear, Publisher publisher, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.isn = isn;
        this.name = name;
        this.publishingYear = publishingYear;
        this.publisher = publisher;
        this.isLendable = isLendable;
        this.numberOfPages = numberOfPages;
        this.lendPeriodInDays = lendPeriodInDays;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Literature{" +
                "id='" + id + '\'' +
                ", isn='" + isn + '\'' +
                ", name='" + name + '\'' +
                ", publishingYear=" + publishingYear +
                ", publisher=" + publisher +
                ", isLendable=" + isLendable +
                ", numberOfPages=" + numberOfPages +
                ", lendPeriodInDays=" + lendPeriodInDays +
                ", description='" + description + '\'' +
                ", created_at=" + createdAt +
                ", updated_at=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literature that = (Literature) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
