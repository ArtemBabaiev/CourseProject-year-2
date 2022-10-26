package edu.chnu.library.form;

import lombok.*;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 21.10.2022 12:20
 * @class QueryForm
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryForm {
    String type;
    String query;
}
