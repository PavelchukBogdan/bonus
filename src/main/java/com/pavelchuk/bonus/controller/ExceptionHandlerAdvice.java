package com.pavelchuk.bonus.controller;

import com.pavelchuk.bonus.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

//    TODO передалать на обработку BusinessException
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handlerBuisnesException(BusinessException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
