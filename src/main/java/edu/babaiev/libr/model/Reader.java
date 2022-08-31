package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 12:37
 * @class Reader
 */
@Entity
@Table(name = "readers")
@Document("readers")
@Getter
@Setter
@NoArgsConstructor
public abstract class Reader {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String readerTicket;
    private String phoneNumber;
    private LocalDate birthday;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Reader(String id) {
        this.id = id;
    }

    public Reader(String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime updated_at) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.readerTicket = readerTicket;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Reader(String id, String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.readerTicket = readerTicket;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id.equals(reader.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", readerTicket='" + readerTicket + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                ", created_at=" + created_at +
                ", update_at=" + updated_at +
                '}';
    }
}
