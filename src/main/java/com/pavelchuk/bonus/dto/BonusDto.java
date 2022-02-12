package com.pavelchuk.bonus.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BonusDto {

    private LocalDateTime createdAt;
    private BigDecimal amount;

}
