package edu.chnu.library.exception;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 27.10.2022 12:53
 * @class LiteratureNotLendableException
 */
public class LiteratureNotLendableException extends RuntimeException {

    public LiteratureNotLendableException() {
    }

    public LiteratureNotLendableException(String message) {
        super(message);
    }

    public LiteratureNotLendableException(String message, Throwable cause) {
        super(message, cause);
    }
}
