package edu.babaiev.libr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 22:34
 * @class Monograph
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Monograph extends Literature{
    @DBRef
    @ManyToOne
    private Author author;
    @DBRef
    @ManyToOne
    private MonographType monographType;
    private String branch;
    private String defenseCity;

    public Monograph(String id) {
        super(id);
    }

    public Monograph(String isn, String name, int publishingYear, Publisher publisher, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime update_at, Author author, MonographType monographType, String branch, String defenseCity) {
        super(isn, name, publishingYear, publisher, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, update_at);
        this.author = author;
        this.monographType = monographType;
        this.branch = branch;
        this.defenseCity = defenseCity;
    }

    public Monograph(String id, String isn, String name, int publishingYear, Publisher publisher, int stock, boolean isLendable, int numberOfPages, int lendPeriodInDays, String description, LocalDateTime created_at, LocalDateTime update_at, Author author, MonographType monographType, String branch, String defenseCity) {
        super(id, isn, name, publishingYear, publisher, stock, isLendable, numberOfPages, lendPeriodInDays, description, created_at, update_at);
        this.author = author;
        this.monographType = monographType;
        this.branch = branch;
        this.defenseCity = defenseCity;
    }

    @Override
    public String toString() {
        return "Monograph{" +
                "author=" + author +
                ", monographType=" + monographType +
                ", branch='" + branch + '\'' +
                ", defenseCity='" + defenseCity + '\'' +
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
