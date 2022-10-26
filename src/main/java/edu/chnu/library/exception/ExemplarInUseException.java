package edu.chnu.library.exception;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.10.2022 19:28
 * @class ExemplarInUseException
 */
public class ExemplarInUseException extends RuntimeException{
    public ExemplarInUseException() {
    }

    public ExemplarInUseException(String message) {
        super(message);
    }

    public ExemplarInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
