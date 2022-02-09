package com.pavelchuk.bonus.repository;

import com.pavelchuk.bonus.dto.CreateBonus;

import java.util.UUID;


public interface BonusRepository {

    boolean existsByIdempotencyKey(UUID idempotencyKey);

    int softDeleteById(UUID uuid);

    void createBonus(CreateBonus bonus);


}
