package edu.chnu.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

/**
 * @author artem
 * @version: 1.0.0
 * @project microJava_01
 * @date 12.09.2022 23:55
 * @class ApiExceptionHandler
 */
@ControllerAdvice(basePackages = {"edu.chnu.library.controller.api"})
public class ApiExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleApiNotFoundException(NotFoundException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleApiBadRequestException(BadRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleApiRuntimeException(RuntimeException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
