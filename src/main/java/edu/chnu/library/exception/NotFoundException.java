package edu.chnu.library.exception;

/**
 * @author artem
 * @version: 1.0.0
 * @project microJava_01
 * @date 12.09.2022 23:50
 * @class NotFoundException
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
