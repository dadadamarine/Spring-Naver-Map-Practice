package com.mappractice.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestReponseEntityExceptionHandler {
    private Logger log = LoggerFactory.getLogger(RestReponseEntityExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ImageNotFoundException.class)
    public void ImageNotFound(){
        log.debug("Image is not found");
    }
}
