package edu.babaiev.libr.factory;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 01:23
 * @class QuantityConfig
 */
public enum QuantityConfig {
    ADULT(5), ARTICLE(5), AUTHOR(10),
    BOOK(5), BOOK_CASE(10), BOOK_TYPE(5),
    COLLECTION(5), COLLECTION_TYPE(3),
    EMPLOYEE(5),
    GENRE(5),
    KEY(5),
    LIBRARY(2),
    MONOGRAPH(5), MONOGRAPH_TYPE(2),
    PERIODICAL(5), PERIODICAL_TYPE(2), PUBLISHER(5), PUPIL(5),
    READING_ROOM(5), RECORD(5), ROLE(4),
    SCIENTIST(5), SHELF(20), STUDENT(5), SUBJECT(5),
    WRITING(10), WRITTEN_OFF(10);
    private final int value;

    QuantityConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
