package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateClient {
    private String name;;
    private UUID idempotencyKey;

}
