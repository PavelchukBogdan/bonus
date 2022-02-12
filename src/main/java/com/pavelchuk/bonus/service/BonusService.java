package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.CreateBonusDto;

import java.util.UUID;

public interface BonusService {

    void createBonus(CreateBonusDto bonus);

    void deleteBonus(UUID uuid);

}
