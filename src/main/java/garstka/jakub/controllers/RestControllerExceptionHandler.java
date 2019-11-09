package garstka.jakub.controllers;

import garstka.jakub.config.InvalidNumberExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static garstka.jakub.config.Constants.INVALID_NUMBER_MESSAGE;
import static garstka.jakub.config.Constants.INVALID_URL_MESSAGE;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    ResponseEntity<Object> handleInvalidArgumentException(RuntimeException runtimeException, WebRequest webRequest) {
        return handleExceptionInternal(runtimeException, INVALID_URL_MESSAGE, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = {InvalidNumberExceptions.class})
    ResponseEntity<Object> handleInvalidNumberException(RuntimeException runtimeException, WebRequest webRequest) {
        return handleExceptionInternal(runtimeException, INVALID_NUMBER_MESSAGE, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, webRequest);
    }
}
