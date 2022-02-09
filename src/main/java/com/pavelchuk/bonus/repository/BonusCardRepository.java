package com.pavelchuk.bonus.repository;

import com.pavelchuk.bonus.dto.CreateBonusCard;
import com.pavelchuk.bonus.dto.CreateClient;

import java.util.UUID;

public interface BonusCardRepository {

    boolean existsByIdempotencyKey(UUID idempotencyKey);

    int blockedClientCardByClientId(UUID uuid);

    int blockedClientCardId(UUID uuid);

    int deleteByClientId(UUID clientId);

    int deleteById(UUID clientId);

    void createBonusCard(CreateBonusCard bonusCard);



}
