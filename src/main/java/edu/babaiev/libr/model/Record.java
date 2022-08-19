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

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 22:39
 * @class Record
 */
@Entity
@Table(name = "records")
@Document("records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    private String id;
    @DBRef
    @ManyToOne
    private Reader reader;
    @DBRef
    @ManyToOne
    private Employee lendBy;
    @DBRef
    @ManyToOne
    private Employee acceptedBy;
    @DBRef
    @ManyToOne
    private Literature literature;
    private LocalDateTime lend_at;
    private LocalDateTime returned_at;
    private LocalDateTime created_at;
    private LocalDateTime update_at;

    public Record(String id) {
        this.id = id;
    }

    public Record(Reader reader, Employee lendBy, Employee acceptedBy, Literature literature, LocalDateTime lend_at, LocalDateTime returned_at, LocalDateTime created_at, LocalDateTime update_at) {
        this.reader = reader;
        this.lendBy = lendBy;
        this.acceptedBy = acceptedBy;
        this.literature = literature;
        this.lend_at = lend_at;
        this.returned_at = returned_at;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id.equals(record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", reader=" + reader +
                ", lendBy=" + lendBy +
                ", acceptedBy=" + acceptedBy +
                ", literature=" + literature +
                ", lend_at=" + lend_at +
                ", returned_at=" + returned_at +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }
}
