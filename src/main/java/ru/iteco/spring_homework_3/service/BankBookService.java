package ru.iteco.spring_homework_3.service;

import ru.iteco.spring_homework_3.model.BankBookDto;

import java.util.List;

public interface BankBookService {

    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto findById(Integer bankBookId);
    BankBookDto createBankBook(BankBookDto bankBookDto);
    BankBookDto updateBankBook(BankBookDto bankBookDto);
    Integer deleteBankBookById(Integer bankBookId);
    Integer deleteBankBookByUserId(Integer userId);
}
