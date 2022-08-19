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
 * @class Key
 */
@Entity
@Table(name = "keys")
@Document("keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    @Id
    private String id;
    @Column(unique = true)
    private String login;
    private String password;
    @OneToOne
    @DBRef
    private Role role;
    private LocalDateTime created_at;
    private LocalDateTime update_at;

    public Key(String id) {
        this.id = id;
    }

    public Key(String login, String password, Role role, LocalDateTime created_at, LocalDateTime update_at) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return id.equals(key.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Key{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                '}';
    }
}
