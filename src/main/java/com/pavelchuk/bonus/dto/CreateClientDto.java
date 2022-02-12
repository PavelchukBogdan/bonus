package com.pavelchuk.bonus.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CreateClientDto {

    @NotBlank
    @Pattern(regexp = "\\w{1,255}")
    private String name;

    @NotBlank
    private UUID idempotencyKey;

}
