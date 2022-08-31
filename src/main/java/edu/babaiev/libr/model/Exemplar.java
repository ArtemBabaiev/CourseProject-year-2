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
 * @date 31.08.2022 14:56
 * @class Exemplar
 */
@Entity
@Table(name = "exemplars")
@Document("exemplars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exemplar {
    @Id
    private String id;
    private boolean isLend;
    @DBRef
    @ManyToOne
    private Shelf shelf;
    @DBRef
    @ManyToOne
    private Literature literature;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Exemplar(String id) {
        this.id = id;
    }

    public Exemplar(boolean isLend, Shelf shelf, Literature literature, LocalDateTime created_at, LocalDateTime updated_at) {
        this.isLend = isLend;
        this.shelf = shelf;
        this.literature = literature;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplar exemplar = (Exemplar) o;
        return id.equals(exemplar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Exemplar{" +
                "id='" + id + '\'' +
                ", isLend=" + isLend +
                ", shelf=" + shelf +
                ", literature=" + literature +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
