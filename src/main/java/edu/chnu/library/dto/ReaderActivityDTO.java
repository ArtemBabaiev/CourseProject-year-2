package edu.chnu.library.dto;

import edu.chnu.library.model.Reader;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 25.10.2022 23:59
 * @class ReaderActivityDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ReaderActivityDTO extends Reader{
    private LocalDateTime lastTime;

    public ReaderActivityDTO(String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastTime) {
        super(firstName, lastName, address, readerTicket, phoneNumber, birthday, createdAt, updatedAt);
        this.lastTime = lastTime;
    }

    public ReaderActivityDTO(String id, String firstName, String lastName, String address, String readerTicket, String phoneNumber, LocalDate birthday, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastTime) {
        super(id, firstName, lastName, address, readerTicket, phoneNumber, birthday, createdAt, updatedAt);
        this.lastTime = lastTime;
    }
}
