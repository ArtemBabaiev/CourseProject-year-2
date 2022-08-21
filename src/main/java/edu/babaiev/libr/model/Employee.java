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
 * @date 15.08.2022 12:36
 * @class Employee
 */
@Entity
@Table(name = "employees")
@Document("employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String id;
    private String name;
    @DBRef
    @ManyToOne
    private ReadingRoom readingRoom;
    @DBRef
    @OneToOne
    private Key key;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String name, ReadingRoom readingRoom, Key key, LocalDateTime created_at, LocalDateTime updated_at) {
        this.name = name;
        this.readingRoom = readingRoom;
        this.key = key;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", readingRoom=" + readingRoom +
                ", key=" + key +
                ", created_at=" + created_at +
                ", update_at=" + updated_at +
                '}';
    }
}
