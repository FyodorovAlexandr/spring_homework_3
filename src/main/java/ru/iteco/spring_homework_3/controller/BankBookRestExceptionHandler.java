package ru.iteco.spring_homework_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.spring_homework_3.exceptions.BankBookException;
import ru.iteco.spring_homework_3.exceptions.BankBookNotFoundException;
import ru.iteco.spring_homework_3.model.ErrorDto;

@RestControllerAdvice
public class BankBookRestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BankBookNotFoundException.class)
    public ErrorDto handleBankBookNotFoundException(BankBookNotFoundException exception) {
        return new ErrorDto(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookException.class)
    public ErrorDto handleBankBookException(BankBookException bankBookException){
        return new ErrorDto(HttpStatus.BAD_REQUEST.name(), bankBookException.getMessage());
    }


}
