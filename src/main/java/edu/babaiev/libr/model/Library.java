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
 * @date 15.08.2022 12:36
 * @class Library
 */
@Entity
@Table(name = "libraries")
@Document("libraries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id
    private String id;
    private String number;
    private String address;
    private LocalDateTime created_at;
    private LocalDateTime update_at;

    public Library(String id) {
        this.id = id;
    }

    public Library(String number, String address, LocalDateTime created_at, LocalDateTime update_at) {
        this.number = number;
        this.address = address;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id.equals(library.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }
}
