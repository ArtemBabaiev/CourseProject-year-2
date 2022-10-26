package edu.chnu.library.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 10.09.2022 21:29
 * @class ReaderChooseForm
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderChooseForm {
    private String category;

    @Override
    public String toString() {
        return "CategoryChooseForm{" +
                "category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderChooseForm that = (ReaderChooseForm) o;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
