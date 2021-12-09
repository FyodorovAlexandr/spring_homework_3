package ru.iteco.spring_homework_3.exceptions;

public class BankBookException extends RuntimeException{
    public BankBookException(String message) {
        super(message);
    }
}
