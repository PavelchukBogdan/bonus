package com.pavelchuk.bonus.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Client {


    private UUID id;
    private String name;
    private Boolean blocked;
    private Boolean deleted;
    private LocalDateTime blockedAt;
    private LocalDateTime deletedAt;
    private UUID idempotencyKey;


}
