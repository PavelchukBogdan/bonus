package com.pavelchuk.bonus.service;

import com.pavelchuk.bonus.dto.BalanceDetailing;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface BalanceService {

    BigDecimal getBalance(UUID clientId, String code);

    List<BalanceDetailing> getBalanceByMonth(UUID clientId, String currency, YearMonth period);
}
