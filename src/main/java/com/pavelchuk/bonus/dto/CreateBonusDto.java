package com.pavelchuk.bonus.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateBonusDto {

    @NotBlank
    private UUID bonusCardId;

    @NotBlank
    private BigDecimal amount;

    @NotBlank
    private UUID idempotencyKey;
}
