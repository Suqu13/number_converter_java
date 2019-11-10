package garstka.jakub.controllers;

import garstka.jakub.config.exceptions.InvalidNumberException;
import garstka.jakub.config.exceptions.InvalidNumeralSystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidNumeralSystemException.class})
    ResponseEntity<Object> handleInvalidArgumentException(RuntimeException runtimeException, WebRequest webRequest) {
        return new ResponseEntity<Object>(runtimeException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidNumberException.class})
    ResponseEntity<Object> handleInvalidNumberException(RuntimeException runtimeException, WebRequest webRequest) {
        return new ResponseEntity<Object>(runtimeException.getMessage(), new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
