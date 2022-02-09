package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.CreateBonus;

import java.util.UUID;

public interface BonusService {

    void createBonus(CreateBonus bonus);

    void deleteBonus(UUID uuid);

}
