package com.pavelchuk.bonus.service;


import com.pavelchuk.bonus.dto.CreateBonusDto;
import com.pavelchuk.bonus.entity.Bonus;
import com.pavelchuk.bonus.exception.BusinessException;
import com.pavelchuk.bonus.mapper.BonusMapper;
import com.pavelchuk.bonus.repository.BonusRepository;
import com.pavelchuk.bonus.service.impl.BonusServiceImpl;
import com.pavelchuk.bonus.util.BusinessExceptionCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        BonusServiceImpl.class,
})
public class BonusServiceTest {

    @Autowired
    private BonusService bonuservice;

    @MockBean
    private BonusRepository bonusRepository;
    @MockBean
    private BonusMapper bonusMapper;

    @AfterEach
    void noMoreInteractions() {
        verifyNoMoreInteractions(bonusRepository);
        verifyNoMoreInteractions(bonusMapper);
    }

    @Test
    @DisplayName("Create bonus test, positive case")
    void createBonusCardTest() {
        CreateBonusDto dto = new CreateBonusDto();
        dto.setIdempotencyKey(UUID.randomUUID());
        dto.setAmount(BigDecimal.valueOf(200));
        dto.setBonusCardId(UUID.randomUUID());

        Bonus bonus = new Bonus();
        doReturn(bonus).when(bonusMapper).mapCreateBonusDtoToBonus(dto);
        doReturn(new Bonus()).when(bonusRepository).save(bonus);

        // when
        bonuservice.createBonus(dto);

        //then
        verify(bonusMapper).mapCreateBonusDtoToBonus(dto);
        verify(bonusRepository).save(bonus);
    }

    @Test
    @DisplayName("Delete bonus by Id, positive case")
    void deleteBonusByIdTest() {
        UUID id = UUID.randomUUID();
        Bonus bonus = new Bonus();


        doReturn(bonus).when(bonusRepository).getById(id);
        doReturn(new Bonus()).when(bonusRepository).save(any(Bonus.class));


        // when
        bonuservice.deleteBonus(id);

        //then
        verify(bonusRepository).getById(id);
        verify(bonusRepository).save(any(Bonus.class));

    }

    @Test
    @DisplayName("Delete bonus by Id, client not found")
    void deleteByIdClientNotFountTest() {
        UUID id = UUID.randomUUID();
        doReturn(null).when(bonusRepository).getById(id);


        // when
        BusinessException businessException = assertThrows(BusinessException.class, () -> bonuservice.deleteBonus(id));

        //then
        verify(bonusRepository).getById(id);
        assertEquals(businessException.getMessage(), BusinessExceptionCode.BONUS_NOT_FOUND);

    }


}
