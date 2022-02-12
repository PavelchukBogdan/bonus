package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.CreateBonusCardDto;

import java.util.UUID;

public interface BonusCardService {

   void deleteBonusCardById(UUID uuid);

   void deleteBonusCardByClientId(UUID clientId);

   void blockedBonusCardById(UUID uuid);

   void blockedBonusCardByClientId(UUID clientId);

   void createBonusCard(CreateBonusCardDto bonusCard);
}
