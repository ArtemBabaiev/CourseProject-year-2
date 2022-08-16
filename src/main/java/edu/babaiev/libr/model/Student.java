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
 * @date 16.08.2022 19:17
 * @class Student
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends Reader{

    private String university;
    private String faculty;
    private int course;
    private String group;

    public Student(String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime update_at, String university, String faculty, int course, String group) {
        super(firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, update_at);
        this.university = university;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public Student(String id, String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime created_at, LocalDateTime update_at, String university, String faculty, int course, String group) {
        super(id, firstName, lastName, address, readerTicket, phoneNumber, birthday, created_at, update_at);
        this.university = university;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
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
        return "Student{" +
                "university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course=" + course +
                ", group='" + group + '\'' +
                "} " + super.toString();
    }
}
