package ru.alexandrov.backend.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.logging.Logger;

@ControllerAdvice
@Slf4j
public class Handler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.info(e.getMessage());
        log.info(Arrays.toString(e.getStackTrace()));
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage() + "\n------------------\n" + Arrays.toString(e.getStackTrace()));
    }
}
