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
 * @date 26.08.2022 23:55
 * @class LiteratureChooseForm
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LiteratureChooseForm {
    private String category;

    @Override
    public String toString() {
        return "LiteratureChooseForm{" +
                "category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiteratureChooseForm that = (LiteratureChooseForm) o;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
