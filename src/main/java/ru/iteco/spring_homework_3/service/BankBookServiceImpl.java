package ru.iteco.spring_homework_3.service;

import org.springframework.stereotype.Component;
import ru.iteco.spring_homework_3.exceptions.BankBookException;
import ru.iteco.spring_homework_3.exceptions.BankBookNotFoundException;
import ru.iteco.spring_homework_3.model.BankBookDto;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBookDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        int id = sequenceId.getAndIncrement();
        bankBookDtoMap.put(id, BankBookDto.builder()
                .id(id)
                .userId(2)
                .number("1233133")
                .amount(new BigDecimal("100.0"))
                .currency("USD")
                .build());

        id = sequenceId.getAndIncrement();
        bankBookDtoMap.put(id, BankBookDto.builder()
                .id(id)
                .userId(3)
                .number("2312341")
                .amount(new BigDecimal("150.0"))
                .currency("RUB")
                .build());

        id = sequenceId.getAndIncrement();
        bankBookDtoMap.put(id, BankBookDto.builder()
                .id(id)
                .userId(2)
                .number("3123245")
                .amount(new BigDecimal("200.0"))
                .currency("EUR")
                .build());

        id = sequenceId.getAndIncrement();
        bankBookDtoMap.put(id, BankBookDto.builder()
                .id(id)
                .userId(1)
                .number("2341234")
                .amount(new BigDecimal("100.0"))
                .currency("LIR")
                .build());

        id = sequenceId.getAndIncrement();
        bankBookDtoMap.put(id, BankBookDto.builder()
                .id(id)
                .userId(1)
                .number("4312451")
                .amount(new BigDecimal("400.0"))
                .currency("EUR")
                .build());
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {
        List<BankBookDto> list = new ArrayList<>();

        for (BankBookDto bankBookDto : bankBookDtoMap.values()) {
            if (bankBookDto.getUserId().equals(userId)) {
                list.add(bankBookDto);
            }
        }

        if (list.isEmpty()) {
            throw new BankBookNotFoundException("У пользователя id = " + userId + " нет счетов");
        }
        return list;
    }

    @Override
    public BankBookDto findById(Integer bankBookId) {
        BankBookDto bankBookDto = bankBookDtoMap.get(bankBookId);

        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Счет id = " + bankBookId + " не существует");
        }
        return bankBookDto;
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) {
        int id = sequenceId.getAndIncrement();
        bankBookDto.setId(id);

        for (BankBookDto bookDto : bankBookDtoMap.values()) {
            if (bankBookDto.getUserId().equals(bookDto.getUserId())
                    && bankBookDto.getNumber().equals(bookDto.getNumber())) {
                if (bankBookDto.getCurrency().equals(bookDto.getCurrency())) {
                    throw new BankBookException("Счет " + bankBookDto.getCurrency() +
                            " с номером " + bankBookDto.getNumber() + " уже существует у пользователя id = " + bookDto.getId());
                }
            }
        }
        bankBookDtoMap.put(id, bankBookDto);
        return bankBookDto;
    }

    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) {
        BankBookDto bankBookId = bankBookDtoMap.get(bankBookDto.getId());
        if (bankBookId == null) {
            throw new BankBookException("Счет не найден");
        } else {
            if (!bankBookDto.getNumber().equals(bankBookId.getNumber())) {
                throw new BankBookException("Номер счета изменять нельзя");
            } else {
                bankBookDtoMap.put(bankBookDto.getId(), bankBookDto);
            }
        }
        return bankBookDto;
    }

    @Override
    public Integer deleteBankBookById(Integer bankBookId) {
        if (bankBookDtoMap.get(bankBookId) == null) {
            throw new BankBookException("Счет id = " + bankBookId + " не существует");
        } else {
            bankBookDtoMap.remove(bankBookId);
        }
        return 200;
    }

    @Override
    public Integer deleteBankBookByUserId(Integer userId) {
        List<Integer> listId = new ArrayList<>();

        for (BankBookDto bankBookDto : bankBookDtoMap.values()) {
            if (bankBookDto.getUserId().equals(userId)) {
                listId.add(bankBookDto.getId());
            }
        }

        if (listId.isEmpty()) {
            throw new BankBookException("У пользователя userId = " + userId + " нет счетов");
        } else {
            for (Integer id : listId) {
                bankBookDtoMap.remove(id);
            }
        }
        return 200;
    }
}