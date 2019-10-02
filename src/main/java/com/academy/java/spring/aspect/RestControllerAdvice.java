package com.academy.java.spring.aspect;

import com.academy.java.spring.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;


@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorDTO> exceptionHandler(ValidationException e) {

        ErrorDTO ret = ErrorDTO.Builder.newError(MDC.get("X-B3-TraceId"), e.getMessage());

        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ErrorDTO> exceptionHandler(Exception e) {

        ErrorDTO ret = ErrorDTO.Builder.newError(MDC.get("X-B3-TraceId"), e.getMessage());

        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(ret, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}