package edu.chnu.library.form;

import edu.chnu.library.model.Employee;
import edu.chnu.library.model.Key;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 23.10.2022 17:00
 * @class UserForm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String id;
    private String name;
    private ReadingRoom readingRoom;
    private String login;
    private String password;
    private Role role;

    public UserForm(Employee employee, Key key){
        name = employee.getName();
        readingRoom = employee.getReadingRoom();

        login = key.getLogin();
        password = key.getPassword();
        role = key.getRole();
    }

}
