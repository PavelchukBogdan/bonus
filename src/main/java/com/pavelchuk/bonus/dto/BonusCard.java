package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BonusCard {

    private UUID id;
    private String code;
    private String number;
    private Boolean deleted;
    private Boolean blocked ;
    private LocalDateTime blockedAt;
    private LocalDateTime deletedAt;
    private UUID idempotencyKey;

}
