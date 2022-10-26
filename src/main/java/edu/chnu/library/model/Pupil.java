package edu.chnu.library.model;

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
 * @date 16.08.2022 19:14
 * @class Pupil
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pupil extends Reader {
    private String school;
    private String form;

    public Pupil(String id) {
        super(id);
    }

    public Pupil(String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime updated_at, String school, String form) {
        super(firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, updated_at);
        this.school = school;
        this.form = form;
    }

    public Pupil(String id, String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime updated_at, String school, String form) {
        super(id, firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, updated_at);
        this.school = school;
        this.form = form;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "school='" + school + '\'' +
                ", form='" + form + '\'' +
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
