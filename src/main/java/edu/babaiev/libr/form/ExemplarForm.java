package edu.babaiev.libr.form;

import edu.babaiev.libr.model.Shelf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 01.09.2022 00:48
 * @class ExemplarForm
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExemplarForm {
    Shelf shelf;
    int quantity;
}
