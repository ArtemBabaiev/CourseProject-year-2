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
 * @date 17.08.2022 22:23
 * @class Scientist
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scientist extends Reader{
    private String profession;
    private String organisation;

    public Scientist(String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime update_at, String profession, String organisation) {
        super(firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, update_at);
        this.profession = profession;
        this.organisation = organisation;
    }

    public Scientist(String id, String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime update_at, String profession, String organisation) {
        super(id, firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, update_at);
        this.profession = profession;
        this.organisation = organisation;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "profession='" + profession + '\'' +
                ", organisation='" + organisation + '\'' +
                "} " + super.toString();
    }
}
