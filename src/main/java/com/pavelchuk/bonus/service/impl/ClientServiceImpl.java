package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.CreateClient;
import com.pavelchuk.bonus.repository.BonusCardRepository;
import com.pavelchuk.bonus.repository.ClientRepository;
import com.pavelchuk.bonus.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;
    private final BonusCardRepository bonusCardRepository;



    @Override
    @Transactional
    public void deleteClient(UUID clientId) {
        int deletedClientsCount = clientRepository.softDeleteById(clientId);
        if (deletedClientsCount < 1) {
            throw new IllegalArgumentException(String.format("Client with id = %s is not present!", clientId));
        }
        bonusCardRepository.deleteByClientId(clientId);

    }

    @Override
    @Transactional
    public void blockedById(UUID uuid) {
        int blockedClientCount = clientRepository.blockedById(uuid);
        if (blockedClientCount < 1) {
            throw new IllegalArgumentException(String.format("Bonus card with id = %s is not present!", uuid));
        }
        bonusCardRepository.blockedClientCardByClientId(uuid);

    }

    @Override
    public void createClient(CreateClient client) {
        if (!clientRepository.existsByIdempotencyKey(client.getIdempotencyKey())){
            clientRepository.createClient(client);
        } else {
            log.warn("");
        }

    }


}


