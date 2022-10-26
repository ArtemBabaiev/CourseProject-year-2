package edu.chnu.library.exception;

/**
 * @author artem
 * @version: 1.0.0
 * @project microJava_01
 * @date 19.09.2022 22:02
 * @class BadRequestException
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
