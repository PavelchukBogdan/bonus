package com.pavelchuk.bonus.service.impl;

import com.pavelchuk.bonus.dto.BonusDto;
import com.pavelchuk.bonus.dto.CreateClientDto;
import com.pavelchuk.bonus.entity.Bonus;
import com.pavelchuk.bonus.entity.Client;
import com.pavelchuk.bonus.exception.BusinessException;
import com.pavelchuk.bonus.mapper.BonusMapper;
import com.pavelchuk.bonus.mapper.ClientMapper;
import com.pavelchuk.bonus.repository.BonusRepository;
import com.pavelchuk.bonus.repository.ClientRepository;
import com.pavelchuk.bonus.service.BonusCardService;
import com.pavelchuk.bonus.service.ClientService;
import com.pavelchuk.bonus.util.BusinessExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BonusCardService bonusCardService;
    private final BonusRepository bonusRepository;
    private final ClientMapper clientMapper;
    private final BonusMapper bonusMapper;


    @Override
    public void deleteClient(UUID clientId) {
        Client client = clientRepository.getById(clientId);
        if (client != null) {
            client.setDeleted(true);
            client.setDeletedAt(LocalDateTime.now());
            clientRepository.save(client);
            bonusCardService.deleteBonusCardByClientId(clientId);
            log.debug("Client with id: {} was deleted", clientId);
        } else {
            throw new BusinessException(BusinessExceptionCode.CLIENT_NOT_FOUND);
        }

    }

    @Override
    public void blockById(UUID clientId) {
        Client client = clientRepository.getById(clientId);
        if (client != null) {
            client.setBlocked(true);
            client.setBlockedAt(LocalDateTime.now());
            clientRepository.save(client);
            bonusCardService.blockedBonusCardByClientId(clientId);
            log.debug("Client with id: {} was blocked", clientId);
        } else {
            throw new BusinessException(BusinessExceptionCode.CLIENT_NOT_FOUND);
        }
    }

    @Override
    public void createClient(CreateClientDto dto) {
        Client client = clientMapper.mapCreateClientDtoToClient(dto);
        clientRepository.save(client);
        log.debug("Client was created with name: {}", dto.getName());

    }

    @Override
    public BigDecimal getBalance(UUID clientId, String code) {
        log.debug("Getting the client's balance with client_id: {}", clientId);
        return clientRepository.getClientBalance(clientId, code);

    }

    @Override
    public List<BonusDto> getBalanceByMonth(UUID clientId, String currency, YearMonth period) {
        LocalDateTime firstDayOfMonth = period.atDay(1).atStartOfDay();
        LocalDateTime firstDayOfNextMonth = period.atEndOfMonth().plus(1, DAYS).atStartOfDay();
        List<Bonus> bonuses = bonusRepository.getBalanceByMonth(clientId, currency, firstDayOfMonth, firstDayOfNextMonth);
        log.debug("Query detail for a period: {}", period);
        return bonuses.stream().map(bonusMapper::mapBonusDtoToBonus).collect(Collectors.toList());
    }


}


