package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 22:29
 * @class Periodical
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Periodical extends Literature{
    private String issue;
    @DBRef
    @ManyToOne
    private Subject subject;
    @DBRef
    @ManyToOne
    private PeriodicalType periodicalType;
    @DBRef
    @ManyToMany
    @JoinTable(name = "periodical_has_articles")
    private Set<Article> articles;

    public Periodical(String id) {
        super(id);
    }

    public Periodical(String isn, String name, int publishingYear, Publisher publisher, Shelf shelf, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime update_at, String issue, Subject subject, PeriodicalType periodicalType, Set<Article> articles) {
        super(isn, name, publishingYear, publisher, shelf, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, update_at);
        this.issue = issue;
        this.subject = subject;
        this.periodicalType = periodicalType;
        this.articles = articles;
    }

    public Periodical(String id, String isn, String name, int publishingYear, Publisher publisher, Shelf shelf, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime update_at, String issue, Subject subject, PeriodicalType periodicalType, Set<Article> articles) {
        super(id, isn, name, publishingYear, publisher, shelf, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, update_at);
        this.issue = issue;
        this.subject = subject;
        this.periodicalType = periodicalType;
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "issue='" + issue + '\'' +
                ", subject=" + subject +
                ", periodicalType=" + periodicalType +
                ", articles=" + articles +
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
