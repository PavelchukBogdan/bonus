package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateBonusCard {

    private String code;
    private String number;
    private UUID clientId;
    private UUID idempotencyKey;
}
