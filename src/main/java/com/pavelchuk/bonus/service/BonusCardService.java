package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.CreateBonusCard;

import java.util.UUID;

public interface BonusCardService {

   void deleteBonusCard(UUID uuid);

   void blockedBonusCard(UUID uuid);

   void createBonusCard(CreateBonusCard bonusCard);
}
