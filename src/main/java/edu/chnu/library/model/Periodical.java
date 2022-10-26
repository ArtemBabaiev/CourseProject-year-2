package edu.chnu.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Periodical extends Literature {
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

    public Periodical(String isn, String name, int publishingYear, Publisher publisher, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime updated_at, String issue, Subject subject, PeriodicalType periodicalType, Set<Article> articles) {
        super(isn, name, publishingYear, publisher, isLendable, numberOfPages, lendPeriodInDays, description, created_at, updated_at);
        this.issue = issue;
        this.subject = subject;
        this.periodicalType = periodicalType;
        this.articles = articles;
    }

    public Periodical(String id, String isn, String name, int publishingYear, Publisher publisher, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime updated_at, String issue, Subject subject, PeriodicalType periodicalType, Set<Article> articles) {
        super(id, isn, name, publishingYear, publisher, isLendable, numberOfPages, lendPeriodInDays, description, created_at, updated_at);
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
