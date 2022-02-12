package com.pavelchuk.bonus.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CreateBonusCardDto {

    @NotBlank
    @Pattern(regexp = "RUB|USD")
    private String code;

    @NotBlank
    @Pattern(regexp = "\\d{20}")
    private String number;

    @NotBlank
    private UUID clientId;

    @NotBlank
    private UUID idempotencyKey;
}
