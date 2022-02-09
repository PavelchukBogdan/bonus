package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.CreateBonusCard;
import com.pavelchuk.bonus.dto.CreateClient;
import com.pavelchuk.bonus.repository.BonusCardRepository;
import com.pavelchuk.bonus.service.BonusCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BonusCardServiceImpl implements BonusCardService {


   private final BonusCardRepository bonusCardRepository;


    @Override
    @Transactional
    public void deleteBonusCard(UUID id) {
        int deletedBonusCardCount = bonusCardRepository.deleteById(id);
        if (deletedBonusCardCount < 1) {
            throw new IllegalArgumentException(String.format("Bonus card with id = %s is not present!", id));
        }
    }

    @Override
    public void blockedBonusCard(UUID uuid) {
        int blockedClientCount = bonusCardRepository.blockedClientCardId(uuid);
        if (blockedClientCount < 1) {
            throw new IllegalArgumentException(String.format("Bonus card with id = %s is not present!", uuid));
        }

    }

    @Override
    public void createBonusCard(CreateBonusCard bonusCard) {
        if (!bonusCardRepository.existsByIdempotencyKey(bonusCard.getIdempotencyKey())){
            bonusCardRepository.createBonusCard(bonusCard);
        } else {
            log.warn("");
        }

    }

}
