package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateBonus {

    private UUID bonusCardId;
    private BigDecimal amount;
    private UUID idempotencyKey;
}
