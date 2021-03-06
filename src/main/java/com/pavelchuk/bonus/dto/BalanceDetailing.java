package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BalanceDetailing {

    private BigDecimal amount;
    private LocalDateTime createdAt;

}
