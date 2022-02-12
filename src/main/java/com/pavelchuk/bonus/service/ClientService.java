package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.BonusDto;
import com.pavelchuk.bonus.dto.CreateClientDto;
import com.pavelchuk.bonus.entity.Bonus;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface ClientService {

    void deleteClient(UUID clientID);

    void blockById(UUID uuid);

    void createClient(CreateClientDto client);

    BigDecimal getBalance(UUID clientId, String code);

    List<BonusDto> getBalanceByMonth(UUID clientId, String currency, YearMonth period);
}
