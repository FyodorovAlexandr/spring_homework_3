package ru.iteco.spring_homework_3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class BankBookDto {
    private Integer id;
    private Integer userId;
    private String number;
    private BigDecimal amount;
    private String currency;
}
