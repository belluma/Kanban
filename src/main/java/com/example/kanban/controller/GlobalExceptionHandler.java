package com.example.kanban.controller;

import com.example.kanban.model.CustomError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){

        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null) errorMessage = ex.toString();
        CustomError message = new CustomError(new Date(), errorMessage);

        return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
