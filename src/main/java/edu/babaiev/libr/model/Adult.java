package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 19:30
 * @class Adult
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Adult extends Reader{
    private String workPlace;

    public Adult(String id) {
        super(id);
    }

    public Adult(String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime updated_at, String workPlace) {
        super(firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, updated_at);
        this.workPlace = workPlace;
    }

    public Adult(String id, String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime updated_at, String workPlace) {
        super(id, firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, updated_at);
        this.workPlace = workPlace;
    }

    @Override
    public String toString() {
        return "Adult{" +
                "workPlace='" + workPlace + '\'' +
                "} " + super.toString();
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
