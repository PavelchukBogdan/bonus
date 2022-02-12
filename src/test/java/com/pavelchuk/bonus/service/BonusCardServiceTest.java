package com.pavelchuk.bonus.service;


import com.pavelchuk.bonus.dto.CreateBonusCardDto;
import com.pavelchuk.bonus.entity.BonusCard;
import com.pavelchuk.bonus.exception.BusinessException;
import com.pavelchuk.bonus.mapper.BonusCardMapper;
import com.pavelchuk.bonus.repository.BonusCardRepository;
import com.pavelchuk.bonus.service.impl.BonusCardServiceImpl;
import com.pavelchuk.bonus.util.BusinessExceptionCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        BonusCardServiceImpl.class,
})
public class BonusCardServiceTest {

    @Autowired
    private BonusCardService bonusCardService;

    @MockBean
    private BonusCardRepository bonusCardRepository;
    @MockBean
    private BonusCardMapper bonusCardMapper;

    @AfterEach
    void noMoreInteractions() {
        verifyNoMoreInteractions(bonusCardRepository);
        verifyNoMoreInteractions(bonusCardMapper);
    }

    @Test
    @DisplayName("Create bonusCard test, positive case")
    void createBonusCardTest() {
        CreateBonusCardDto dto = new CreateBonusCardDto();
        dto.setIdempotencyKey(UUID.randomUUID());
        dto.setCode("USD");
        dto.setNumber("1111111111");
        dto.setClientId(UUID.randomUUID());
        BonusCard bonusCard = new BonusCard();
        doReturn(bonusCard).when(bonusCardMapper).mapCreateBonusCardDtoToBonusCard(dto);
        doReturn(new BonusCard()).when(bonusCardRepository).save(bonusCard);

        // when
        bonusCardService.createBonusCard(dto);

        //then
        verify(bonusCardMapper).mapCreateBonusCardDtoToBonusCard(dto);
        verify(bonusCardRepository).save(bonusCard);
    }

    @Test
    @DisplayName("Block bonusCard by Id, positive case")
    void blockBonusCardByIdTest() {
        UUID id = UUID.randomUUID();
        BonusCard bonusCard = new BonusCard();

        doReturn(bonusCard).when(bonusCardRepository).getById(id);
        doReturn(new BonusCard()).when(bonusCardRepository).save(any(BonusCard.class));


        // when
        bonusCardService.blockedBonusCardById(id);

        //then
        verify(bonusCardRepository).getById(id);
        verify(bonusCardRepository).save(any(BonusCard.class));

    }

    @Test
    @DisplayName("Block bonusCard by Id, client not found")
    void blockByIdBonusCardNotFountTest() {
        UUID id = UUID.randomUUID();
        doReturn(null).when(bonusCardRepository).getById(id);


        // when
        BusinessException businessException = assertThrows(BusinessException.class, () -> bonusCardService.blockedBonusCardById(id));

        //then
        verify(bonusCardRepository).getById(id);
        assertEquals(businessException.getMessage(), BusinessExceptionCode.CARD_NOT_FOUND);

    }

    @Test
    @DisplayName("Delete bonusCard by Id, positive case")
    void deleteBonusCardByIdTest() {
        UUID id = UUID.randomUUID();
        BonusCard bonusCard = new BonusCard();


        doReturn(bonusCard).when(bonusCardRepository).getById(id);
        doReturn(new BonusCard()).when(bonusCardRepository).save(any(BonusCard.class));


        // when
        bonusCardService.deleteBonusCardById(id);

        //then
        verify(bonusCardRepository).getById(id);
        verify(bonusCardRepository).save(any(BonusCard.class));

    }

    @Test
    @DisplayName("Delete bonusCard by Id, client not found")
    void deleteByIdClientNotFountTest() {
        UUID id = UUID.randomUUID();
        doReturn(null).when(bonusCardRepository).getById(id);


        // when
        BusinessException businessException = assertThrows(BusinessException.class, () -> bonusCardService.deleteBonusCardById(id));

        //then
        verify(bonusCardRepository).getById(id);
        assertEquals(businessException.getMessage(), BusinessExceptionCode.CARD_NOT_FOUND);

    }

}



