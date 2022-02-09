package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.CreateBonus;
import com.pavelchuk.bonus.repository.BonusRepository;
import com.pavelchuk.bonus.service.BonusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;

    @Override
    public void createBonus(CreateBonus bonus) {
        if (!bonusRepository.existsByIdempotencyKey(bonus.getIdempotencyKey())){
            bonusRepository.createBonus(bonus);
        } else {
            log.warn("");
        }
    }

    @Override
    public void deleteBonus(UUID id) {
        int deletedBonusCount = bonusRepository.softDeleteById(id);
        if (deletedBonusCount < 1) {
            throw new IllegalArgumentException(String.format("Bonus with id = %s is not present!", id));
        }

    }


}
