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
import java.util.NoSuchElementException;

import static com.example.kanban.service.TodoService.DBEmptyMessage;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request){
        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null) errorMessage = ex.toString();
        CustomError message = new CustomError(new Date(), errorMessage);

        return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(Exception ex, WebRequest request){
        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null) errorMessage = ex.toString();
        CustomError message = new CustomError(new Date(), errorMessage);
    System.out.println(errorMessage);
        if(errorMessage.equals(DBEmptyMessage)){
        return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.NO_CONTENT);}
        return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
@ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(Exception ex, WebRequest request){
        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null) errorMessage = ex.toString();
        CustomError message = new CustomError(new Date(), errorMessage);

        return new ResponseEntity<Object>(message, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }


}
