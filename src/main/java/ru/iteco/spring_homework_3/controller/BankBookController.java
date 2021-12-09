package ru.iteco.spring_homework_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iteco.spring_homework_3.model.BankBookDto;
import ru.iteco.spring_homework_3.service.BankBookService;

import java.util.List;

@RestController
@RequestMapping("/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService){
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getBankBookByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bankBookService.findByUserId(userId));
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getBankBookById(@PathVariable Integer bankBookId) {
        return ResponseEntity.ok(bankBookService.findById(bankBookId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankBookService.createBankBook(bankBookDto));
    }

    @PutMapping
    public BankBookDto updateBankBook(@RequestBody BankBookDto bankBookDto) {
        return bankBookService.updateBankBook(bankBookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Integer> deleteBankBook(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bankBookService.deleteBankBookById(id));
    }

    @DeleteMapping("/by-user-id/{userId}")
    public ResponseEntity <Integer> deleteBankBookByUserId(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(bankBookService.deleteBankBookByUserId(userId));
    }
}
