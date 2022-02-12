package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.CreateBonusCardDto;
import com.pavelchuk.bonus.entity.BonusCard;
import com.pavelchuk.bonus.exception.BusinessException;
import com.pavelchuk.bonus.mapper.BonusCardMapper;
import com.pavelchuk.bonus.repository.BonusCardRepository;
import com.pavelchuk.bonus.service.BonusCardService;
import com.pavelchuk.bonus.util.BusinessExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BonusCardServiceImpl implements BonusCardService {

   private final BonusCardRepository bonusCardRepository;
   private final BonusCardMapper bonusCardMapper;

    @Override
    public void deleteBonusCardById(UUID id) {
        BonusCard bonusCard = bonusCardRepository.getById(id);
        if (bonusCard != null) {
            bonusCard.setDeleted(true);
            bonusCard.setDeletedAt(LocalDateTime.now());
            bonusCardRepository.save(bonusCard);
            log.debug("Card with id: {} was deleted", id);
        } else {
            throw new BusinessException(BusinessExceptionCode.CARD_NOT_FOUND);
        }
    }

    @Override
    public void deleteBonusCardByClientId(UUID clientId) {
        BonusCard bonusCard = bonusCardRepository.findBonusCardByClientId(clientId);
        if (bonusCard != null) {
            bonusCard.setDeleted(true);
            bonusCard.setDeletedAt(LocalDateTime.now());
            bonusCardRepository.save(bonusCard);
            log.debug("Card for client with client_id: {} was deleted", clientId);
        }   else {
        throw new BusinessException(BusinessExceptionCode.CARD_NOT_FOUND);
    }
    }


    @Override
    public void blockedBonusCardById(UUID id) {
        BonusCard bonusCard = bonusCardRepository.getById(id);
        if (bonusCard != null) {
            bonusCard.setBlocked(true);
            bonusCard.setBlockedAt(LocalDateTime.now());
            bonusCardRepository.save(bonusCard);
            log.debug("Card with id: {} was blocked", id);
        }  else {
            throw new BusinessException(BusinessExceptionCode.CARD_NOT_FOUND);
        }


    }

    @Override
    public void blockedBonusCardByClientId(UUID clientId) {
        BonusCard bonusCard = bonusCardRepository.findBonusCardByClientId(clientId);
        if (bonusCard != null) {
            bonusCard.setBlocked(true);
            bonusCard.setBlockedAt(LocalDateTime.now());
            bonusCardRepository.save(bonusCard);
            log.debug("Card for client with client_id: {} was blocked", clientId);
        }  else {
            throw new BusinessException(BusinessExceptionCode.CARD_NOT_FOUND);
        }

    }

    @Override
    public void createBonusCard(CreateBonusCardDto dto) {
      BonusCard bonusCard = bonusCardMapper.mapCreateBonusCardDtoToBonusCard(dto);
      bonusCardRepository.save(bonusCard);
        log.debug("Card was created with number: {}", dto.getNumber() );


    }

}
