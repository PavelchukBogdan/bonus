package com.pavelchuk.bonus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BalanceDetailing {

    private BigDecimal amount;
    private LocalDateTime createdAt;

}
