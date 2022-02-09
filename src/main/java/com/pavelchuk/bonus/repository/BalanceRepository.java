package com.pavelchuk.bonus.repository;

import com.pavelchuk.bonus.dto.BalanceDetailing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BalanceRepository  {

    BigDecimal getClientBalance(UUID uuid, String code);

    List<BalanceDetailing> getBalanceDetailing(UUID clientId, String currency, LocalDateTime firstDayOfMonth, LocalDateTime lastDayOfMonth);
}
