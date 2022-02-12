package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.CreateClientDto;
import com.pavelchuk.bonus.entity.Client;
import com.pavelchuk.bonus.exception.BusinessException;
import com.pavelchuk.bonus.mapper.ClientMapper;
import com.pavelchuk.bonus.repository.BonusRepository;
import com.pavelchuk.bonus.repository.ClientRepository;
import com.pavelchuk.bonus.service.BonusCardService;
import com.pavelchuk.bonus.service.ClientService;
import com.pavelchuk.bonus.service.impl.ClientServiceImpl;
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
        ClientServiceImpl.class,
})
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private BonusCardService bonusCardService;
    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    private BonusRepository bonusRepository;
    @MockBean
    private ClientMapper clientMapper;

    @AfterEach
    void noMoreInteractions() {
        verifyNoMoreInteractions(clientRepository);
        verifyNoMoreInteractions(clientMapper);
    }

    @Test
    @DisplayName("Create client test, positive case")
    void createClientTest() {
        CreateClientDto dto = new CreateClientDto();
        dto.setIdempotencyKey(UUID.randomUUID());
        dto.setName("name");

        Client client = new Client();


        doReturn(client).when(clientMapper).mapCreateClientDtoToClient(dto);
        doReturn(new Client()).when(clientRepository).save(client);

        // when
        clientService.createClient(dto);

        //then
        verify(clientMapper).mapCreateClientDtoToClient(dto);
        verify(clientRepository).save(client);

    }

    @Test
    @DisplayName("Block client by Id, positive case")
    void blockClientByIdTest() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();


        doReturn(client).when(clientRepository).getById(clientId);
        doReturn(new Client()).when(clientRepository).save(any(Client.class));
        doNothing().when(bonusCardService).blockedBonusCardByClientId(clientId);

        // when
        clientService.blockById(clientId);

        //then
        verify(clientRepository).getById(clientId);
        verify(clientRepository).save(any(Client.class));
        verify(bonusCardService).blockedBonusCardByClientId(clientId);

    }

    @Test
    @DisplayName("Block client by Id, client not found")
    void blockByIdClientNotFountTest() {
        UUID clientId = UUID.randomUUID();
        doReturn(null).when(clientRepository).getById(clientId);


        // when
        BusinessException businessException = assertThrows(BusinessException.class, () -> clientService.blockById(clientId));

        //then
        verify(clientRepository).getById(clientId);
        assertEquals(businessException.getMessage(), BusinessExceptionCode.CLIENT_NOT_FOUND);

    }

    @Test
    @DisplayName("Delete client by Id, positive case")
    void deleteByIdTest() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();


        doReturn(client).when(clientRepository).getById(clientId);
        doReturn(new Client()).when(clientRepository).save(any(Client.class));
        doNothing().when(bonusCardService).deleteBonusCardByClientId(clientId);

        // when
        clientService.deleteClient(clientId);

        //then
        verify(clientRepository).getById(clientId);
        verify(clientRepository).save(any(Client.class));
        verify(bonusCardService).deleteBonusCardByClientId(clientId);

    }

    @Test
    @DisplayName("Delete client by Id, client not found")
    void deleteByIdClientNotFountTest() {
        UUID clientId = UUID.randomUUID();
        doReturn(null).when(clientRepository).getById(clientId);


        // when
        BusinessException businessException = assertThrows(BusinessException.class, () -> clientService.deleteClient(clientId));

        //then
        verify(clientRepository).getById(clientId);
        assertEquals(businessException.getMessage(), BusinessExceptionCode.CLIENT_NOT_FOUND);

    }

}
