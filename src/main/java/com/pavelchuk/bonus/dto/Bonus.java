package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Bonus {


    private UUID id;
    private String bonusCardId;
    private BigDecimal amount;
    private Boolean deleted = false;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private UUID idempotencyKey;



}
