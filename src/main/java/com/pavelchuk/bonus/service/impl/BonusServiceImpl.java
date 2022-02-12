package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.CreateBonusDto;
import com.pavelchuk.bonus.entity.Bonus;
import com.pavelchuk.bonus.exception.BusinessException;
import com.pavelchuk.bonus.mapper.BonusMapper;
import com.pavelchuk.bonus.repository.BonusRepository;
import com.pavelchuk.bonus.service.BonusService;
import com.pavelchuk.bonus.util.BusinessExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;
    private final BonusMapper bonusMapper;


    @Override
    public void createBonus(CreateBonusDto dto) {
        Bonus bonus = bonusMapper.mapCreateBonusDtoToBonus(dto);
        bonus.setCreatedAt(LocalDateTime.now());
        bonusRepository.save(bonus);
        log.debug("Bonus was created for bonus_card: {}", dto.getBonusCardId());

    }

    @Override
    public void deleteBonus(UUID id) {
        Bonus bonus = bonusRepository.getById(id);
        if (bonus != null) {
            bonus.setDeleted(true);
            bonus.setDeletedAt(LocalDateTime.now());
            bonusRepository.save(bonus);
            log.debug("Bonus with id: {} was deleted", id);
        } else {
            throw new BusinessException(BusinessExceptionCode.BONUS_NOT_FOUND);
        }
    }


}
